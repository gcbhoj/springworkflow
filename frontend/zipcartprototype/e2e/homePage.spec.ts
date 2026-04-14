import { RequestRedirect } from './../node_modules/undici-types/fetch.d';
import { test, expect } from '@playwright/test';

test.describe('Home Page UI Tests', () => {
  test('Home Page Display Tests', async ({ page }) => {
    await page.goto('http://localhost:8100/tabs/tab1');

    // wait for Ionic app to fully render
    await page.waitForLoadState('networkidle');

    // verify company name
    await page.getByText('Zip Cart', { exact: true });

    // verify user name
    await page.getByText('John', { exact: true });
    // verify Walmart button
    await page.getByRole('button', { name: 'Walmart' });
  });
});

test.describe('Home Page Button Clicks Tests', () => {
  test('Tab buttons navigate correctly', async ({ page }) => {
    await page.goto('http://localhost:8100/tabs/tab1');

    // Click Scan Items tab
    await page.locator('#tab-button-scanItems').click();
    await expect(page).toHaveURL(/scanItems/);

    // Click Fruits & Veg tab
    await page.locator('#tab-button-fruitsAndVeg').click();
    await expect(page).toHaveURL(/fruitsAndVeg/);

    // Click Tab2
    await page.locator('#tab-button-tab2').click();
    await expect(page).toHaveURL(/tab2/);

    // Click Tab3
    await page.locator('#tab-button-tab3').click();
    await expect(page).toHaveURL(/tab3/);
  });
});

test.describe('Home Page Cart Initialization ', () => {
  test('Must call API after Cancel', async ({ page }) => {
    let apiCalled = false;

    // ✅ Intercept API
    await page.route('**/api/v1/cart**', async (route) => {
      apiCalled = true;

      await route.fulfill({
        json: {
          cartId: '123',
          retailerName: 'hello world',
          budget: 0,
          message: 'HAPPY SHOPPING',
        },
      });
    });

    await page.goto('http://localhost:8100/tabs/tab1');

    // Click Walmart
    await page.getByRole('button', { name: 'Walmart' }).click();

    // Wait for alert and click Cancel
    const alert = page.locator('ion-alert');
    await expect(alert).toBeVisible();

    await alert.getByRole('button', { name: 'Cancel' }).click();

    // ✅ Wait for API call to happen
    await page.waitForTimeout(500); // small buffer for async logic

    // ✅ Assert API was triggered
    expect(apiCalled).toBe(true);
    await expect(page.getByText('HAPPY SHOPPING')).toBeVisible();
  });
});
