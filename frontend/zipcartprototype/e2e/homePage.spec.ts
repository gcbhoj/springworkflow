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
test('Must call API after FIRST Cancel (no budget)', async ({ page }) => {
  let apiCalled = false;

  await page.route('**/api/v1/cart**', async (route) => {
    apiCalled = true;

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

  // Step 1: Click Walmart
  await page.getByRole('button', { name: 'Walmart' }).click();

  // Step 2: FIRST ALERT
  const firstAlert = page.locator('ion-alert').first();
  await expect(firstAlert).toBeVisible();

  // Step 3: CLICK CANCEL (this triggers API with 0 budget)
  await firstAlert.getByRole('button', { name: 'Cancel' }).click();

  // Step 4: Wait for API
  await page.waitForResponse((res) => res.url().includes('/api/v1/cart'));

  // Step 5: Assert API called
  expect(apiCalled).toBe(true);

  // Step 6: Assert UI
  await expect(page.getByText('HAPPY SHOPPING')).toBeVisible();
});
  test('Must call API after Adding Budget', async ({ page }) => {
    let apiCalled = false;

    await page.route('http://localhost:5000/api/v1/cart/', async (route) => {
      apiCalled = true;

      await route.fulfill({
        json: {
          cartId: '123',
          retailerName: 'hello world',
          budget: 100,
          message: 'HAPPY SHOPPING',
        },
      });
    });

    await page.goto('http://localhost:8100/tabs/tab1');

    // Step 1: Click Walmart
    await page.getByRole('button', { name: 'Walmart' }).click();

    // Step 2: First alert (confirm)
    const firstAlert = page.locator('ion-alert').first();
    await expect(firstAlert).toBeVisible();

    await firstAlert.getByRole('button', { name: 'OK' }).click();

    // Step 3: Second alert (budget input)
    const secondAlert = page.locator('ion-alert').last();
    await expect(secondAlert).toBeVisible();

    // Step 4: Fill input in second alert
    const input = secondAlert.locator('input');
    await expect(input).toBeVisible();
    await input.fill('100');

    // Step 5: Confirm budget
    await secondAlert.getByRole('button', { name: 'OK' }).click();

    // Step 6: Wait for API
    await page.waitForResponse((res) =>
      res.url().includes('http://localhost:5000/api/v1/cart/'),
    );

    expect(apiCalled).toBe(true);

    // Step 7: UI check
    await expect(page.getByText('HAPPY SHOPPING')).toBeVisible();
  });
});
