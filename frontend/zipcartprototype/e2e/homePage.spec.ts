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
  test('Must handle FIRST Cancel (no budget)', async ({ page }) => {
    await page.route('**/api/v1/cart**', async (route) => {
      await route.fulfill({
        json: {
          cartId: '123',
          retailerName: 'Walmart',
          budget: 0,
          message: 'HAPPY SHOPPING',
        },
      });
    });

    await page.goto('http://localhost:8100/tabs/tab1');

    await page.getByRole('button', { name: 'Walmart' }).click();

    const firstAlert = page.locator('ion-alert').first();
    await firstAlert.getByRole('button', { name: 'Cancel' }).click();

    await expect(page.getByText('HAPPY SHOPPING')).toBeVisible();
  });
  test('Must handle Adding Budget', async ({ page }) => {
    await page.route('**/api/v1/cart**', async (route) => {
      await route.fulfill({
        json: {
          cartId: '123',
          retailerName: 'Walmart',
          budget: 100,
          message: 'HAPPY SHOPPING',
        },
      });
    });

    await page.goto('http://localhost:8100/tabs/tab1');

    await page.getByRole('button', { name: 'Walmart' }).click();

    const firstAlert = page.locator('ion-alert').first();
    await firstAlert.getByRole('button', { name: 'OK' }).click();

    const secondAlert = page.locator('ion-alert').last();

    await secondAlert.locator('input').fill('100');
    await secondAlert.getByRole('button', { name: 'OK' }).click();

    await expect(page.getByText('HAPPY SHOPPING')).toBeVisible();
  });
  test('Must Reactivate Retailer Button Cancel is Pressed', async ({
    page,
  }) => {
    await page.route('**/api/v1/cart**', async (route) => {
      await route.fulfill({
        json: {
          cartId: '123',
          retailerName: 'Walmart',
          budget: 100,
          message: 'HAPPY SHOPPING',
        },
      });
    });

    await page.goto('http://localhost:8100/tabs/tab1');

    await page.getByRole('button', { name: 'Walmart' }).click();

    const firstAlert = page.locator('ion-alert').first();
    await firstAlert.getByRole('button', { name: 'OK' }).click();

    const secondAlert = page.locator('ion-alert').last();

    await secondAlert.locator('input').fill('100');
    await secondAlert.getByRole('button', { name: 'CANCEL' }).click();

    await page.goto('http://localhost:8100/tabs/tab1');

    const button = await page.getByRole('button', { name: 'Walmart' });
    await expect(button).toBeEnabled();
  });
});
