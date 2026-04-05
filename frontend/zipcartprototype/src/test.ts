// This file is required by karma.conf.js and loads recursively all the .spec and framework files

import 'zone.js/testing';
import { getTestBed, TestBed } from '@angular/core/testing';
import {
  BrowserDynamicTestingModule,
  platformBrowserDynamicTesting,
} from '@angular/platform-browser-dynamic/testing';

import { HttpClientTestingModule } from '@angular/common/http/testing';

// First, initialize the Angular testing environment.
getTestBed().initTestEnvironment(
  BrowserDynamicTestingModule,
  platformBrowserDynamicTesting(),
);

// -----------------------------------------------------------
// GLOBAL PATCHES
// -----------------------------------------------------------

// 1️⃣ Auto-inject HttpClientTestingModule into all tests
const originalConfigureTestingModule = TestBed.configureTestingModule;

(TestBed as any).configureTestingModule = function (moduleDef: any) {
  moduleDef.imports = moduleDef.imports || [];

  // Inject HttpClientTestingModule if missing
  const hasHttpClientTesting = moduleDef.imports.some(
    (i: any) => i === HttpClientTestingModule,
  );
  if (!hasHttpClientTesting) {
    moduleDef.imports.push(HttpClientTestingModule);
  }

  // Fix standalone components: if declared in `declarations`, move to `imports`
  if (moduleDef.declarations) {
    moduleDef.declarations.forEach((comp: any, index: number) => {
      if (comp?.ɵcmp?.standalone) {
        moduleDef.imports.push(comp);
        moduleDef.declarations[index] = null; // remove from declarations
      }
    });
    moduleDef.declarations = moduleDef.declarations.filter(Boolean);
  }

  return originalConfigureTestingModule.call(this, moduleDef);
};
