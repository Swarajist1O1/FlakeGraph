class DummyClass_42973 {
	@Test
	public void testApiDoc() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(TestController.class);
		ApiDoc apiDoc = jsondocScanner.getApiDocs(classes, MethodDisplay.URI).iterator().next();
		Assert.assertEquals("test-controller", apiDoc.getName());
		Assert.assertEquals("a-test-controller", apiDoc.getDescription());
		Assert.assertEquals("1.0", apiDoc.getSupportedversions().getSince());
		Assert.assertEquals("2.12", apiDoc.getSupportedversions().getUntil());
		Assert.assertEquals(ApiAuthType.NONE.name(), apiDoc.getAuth().getType());
		Assert.assertEquals(DefaultJSONDocScanner.ANONYMOUS, apiDoc.getAuth().getRoles().get(0));

		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			
			if (apiMethodDoc.getPath().contains("/name")) {
				Assert.assertEquals(ApiVerb.GET, apiMethodDoc.getVerb().iterator().next());
				Assert.assertEquals("string", apiMethodDoc.getResponse().getJsondocType().getOneLineText());
				Assert.assertEquals("string", apiMethodDoc.getBodyobject().getJsondocType().getOneLineText());
				Assert.assertEquals("200 - OK", apiMethodDoc.getResponsestatuscode());
				for (ApiParamDoc apiParamDoc : apiMethodDoc.getPathparameters()) {
					if(apiParamDoc.getName().equals("name")) {
						Assert.assertEquals("string", apiParamDoc.getJsondocType().getOneLineText());
					}
				}
			}

			if (apiMethodDoc.getPath().contains("/age")) {
				Assert.assertEquals(ApiVerb.GET, apiMethodDoc.getVerb().iterator().next());
				Assert.assertEquals("204", apiMethodDoc.getResponsestatuscode());
				Assert.assertEquals("integer", apiMethodDoc.getResponse().getJsondocType().getOneLineText());
				Assert.assertEquals("integer", apiMethodDoc.getBodyobject().getJsondocType().getOneLineText());
				for (ApiParamDoc apiParamDoc : apiMethodDoc.getPathparameters()) {
					if(apiParamDoc.getName().equals("age")) {
						Assert.assertEquals("integer", apiParamDoc.getJsondocType().getOneLineText());
					}
				}
			}

			if (apiMethodDoc.getPath().contains("/avg")) {
				Assert.assertEquals(ApiVerb.GET, apiMethodDoc.getVerb().iterator().next());
				Assert.assertEquals("long", apiMethodDoc.getResponse().getJsondocType().getOneLineText());
				Assert.assertEquals("long", apiMethodDoc.getBodyobject().getJsondocType().getOneLineText());
				for (ApiParamDoc apiParamDoc : apiMethodDoc.getPathparameters()) {
					if(apiParamDoc.getName().equals("avg")) {
						Assert.assertEquals("long", apiParamDoc.getJsondocType().getOneLineText());
					}
				}
			}

			if (apiMethodDoc.getPath().contains("/map")) {
				Assert.assertEquals(ApiVerb.GET, apiMethodDoc.getVerb().iterator().next());
				Assert.assertEquals("map[string, integer]", apiMethodDoc.getResponse().getJsondocType().getOneLineText());
				Assert.assertEquals("map[string, integer]", apiMethodDoc.getBodyobject().getJsondocType().getOneLineText());
				for (ApiParamDoc apiParamDoc : apiMethodDoc.getPathparameters()) {
					if(apiParamDoc.getName().equals("map")) {
						Assert.assertEquals("map[string, integer]", apiParamDoc.getJsondocType().getOneLineText());
					}
				}
			}

			if (apiMethodDoc.getPath().contains("/parametrizedList")) {
				Assert.assertEquals(ApiVerb.GET, apiMethodDoc.getVerb().iterator().next());
				Assert.assertEquals("list of string", apiMethodDoc.getResponse().getJsondocType().getOneLineText());
				Assert.assertEquals("list of string", apiMethodDoc.getBodyobject().getJsondocType().getOneLineText());
				for (ApiParamDoc apiParamDoc : apiMethodDoc.getPathparameters()) {
					if(apiParamDoc.getName().equals("parametrizedList")) {
						Assert.assertEquals("list of string", apiParamDoc.getJsondocType().getOneLineText());
					}
				}
				
			}

			if (apiMethodDoc.getPath().contains("/wildcardParametrizedList")) {
				Assert.assertEquals(ApiVerb.GET, apiMethodDoc.getVerb().iterator().next());
				Assert.assertEquals("list of wildcard", apiMethodDoc.getResponse().getJsondocType().getOneLineText());
				Assert.assertEquals("list of wildcard", apiMethodDoc.getBodyobject().getJsondocType().getOneLineText());
				for (ApiParamDoc apiParamDoc : apiMethodDoc.getPathparameters()) {
					if(apiParamDoc.getName().equals("wildcardParametrizedList")) {
						Assert.assertEquals("list of wildcard", apiParamDoc.getJsondocType().getOneLineText());
					}
				}
			}

			if (apiMethodDoc.getPath().contains("/LongArray")) {
				Assert.assertEquals(ApiVerb.GET, apiMethodDoc.getVerb().iterator().next());
				Assert.assertEquals("array of long", apiMethodDoc.getResponse().getJsondocType().getOneLineText());
				Assert.assertEquals("array of long", apiMethodDoc.getBodyobject().getJsondocType().getOneLineText());
				for (ApiParamDoc apiParamDoc : apiMethodDoc.getPathparameters()) {
					if(apiParamDoc.getName().equals("LongArray")) {
						Assert.assertEquals("array of long", apiParamDoc.getJsondocType().getOneLineText());
					}
				}
			}

			if (apiMethodDoc.getPath().contains("/longArray")) {
				Assert.assertEquals(ApiVerb.GET, apiMethodDoc.getVerb().iterator().next());
				Assert.assertEquals("array of long", apiMethodDoc.getResponse().getJsondocType().getOneLineText());
				Assert.assertEquals("array of long", apiMethodDoc.getBodyobject().getJsondocType().getOneLineText());
				for (ApiParamDoc apiParamDoc : apiMethodDoc.getPathparameters()) {
					if(apiParamDoc.getName().equals("longArray")) {
						Assert.assertEquals("array of long", apiParamDoc.getJsondocType().getOneLineText());
					}
				}
			}
			
			if (apiMethodDoc.getPath().contains("/version")) {
				Assert.assertEquals(ApiVerb.GET, apiMethodDoc.getVerb().iterator().next());
				Assert.assertEquals("1.0", apiMethodDoc.getSupportedversions().getSince());
				Assert.assertEquals("2.12", apiMethodDoc.getSupportedversions().getUntil());
			}
			
			if (apiMethodDoc.getPath().contains("/child")) {
				Assert.assertEquals(ApiVerb.GET, apiMethodDoc.getVerb().iterator().next());
				Assert.assertEquals("child", apiMethodDoc.getResponse().getJsondocType().getOneLineText());
			}
			
			if (apiMethodDoc.getPath().contains("/pizza")) {
				Assert.assertEquals(ApiVerb.GET, apiMethodDoc.getVerb().iterator().next());
				Assert.assertEquals("customPizzaObject", apiMethodDoc.getResponse().getJsondocType().getOneLineText());
			}
			
			if (apiMethodDoc.getPath().contains("/multiple-request-methods")) {
				Assert.assertEquals(2, apiMethodDoc.getVerb().size());
				Iterator<ApiVerb> iterator = apiMethodDoc.getVerb().iterator();
				Assert.assertEquals(ApiVerb.GET, iterator.next());
				Assert.assertEquals(ApiVerb.POST, iterator.next());
			}
			
		}

		classes.clear();
		classes.add(TestControllerWithBasicAuth.class);
		apiDoc = jsondocScanner.getApiDocs(classes, MethodDisplay.URI).iterator().next();
		Assert.assertEquals("test-controller-with-basic-auth", apiDoc.getName());
		Assert.assertEquals(ApiAuthType.BASIC_AUTH.name(), apiDoc.getAuth().getType());
		Assert.assertEquals("ROLE_USER", apiDoc.getAuth().getRoles().get(0));
		Assert.assertEquals("ROLE_ADMIN", apiDoc.getAuth().getRoles().get(1));
		Assert.assertTrue(apiDoc.getAuth().getTestusers().size() > 0);
		
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/basicAuth")) {
				Assert.assertEquals(ApiAuthType.BASIC_AUTH.name(), apiMethodDoc.getAuth().getType());
				Assert.assertEquals("ROLE_USER", apiMethodDoc.getAuth().getRoles().get(0));
				Assert.assertTrue(apiMethodDoc.getAuth().getTestusers().size() > 0);
			}
			
			if (apiMethodDoc.getPath().contains("/noAuth")) {
				Assert.assertEquals(ApiAuthType.NONE.name(), apiMethodDoc.getAuth().getType());
				Assert.assertEquals(DefaultJSONDocScanner.ANONYMOUS, apiMethodDoc.getAuth().getRoles().get(0));
			}
			
			if (apiMethodDoc.getPath().contains("/undefinedAuthWithAuthOnClass")) {
				Assert.assertEquals(ApiAuthType.BASIC_AUTH.name(), apiMethodDoc.getAuth().getType());
				Assert.assertEquals("ROLE_USER", apiMethodDoc.getAuth().getRoles().get(0));
				Assert.assertEquals("ROLE_ADMIN", apiMethodDoc.getAuth().getRoles().get(1));
			}
			
		}
		
		classes.clear();
		classes.add(TestControllerWithNoAuthAnnotation.class);
		apiDoc = jsondocScanner.getApiDocs(classes, MethodDisplay.URI).iterator().next();
		Assert.assertEquals("test-controller-with-no-auth-annotation", apiDoc.getName());
		Assert.assertNull(apiDoc.getAuth());
		
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/basicAuth")) {
				Assert.assertEquals(ApiAuthType.BASIC_AUTH.name(), apiMethodDoc.getAuth().getType());
				Assert.assertEquals("ROLE_USER", apiMethodDoc.getAuth().getRoles().get(0));
				Assert.assertTrue(apiMethodDoc.getAuth().getTestusers().size() > 0);
			}
			
			if (apiMethodDoc.getPath().contains("/noAuth")) {
				Assert.assertEquals(ApiAuthType.NONE.name(), apiMethodDoc.getAuth().getType());
				Assert.assertEquals(DefaultJSONDocScanner.ANONYMOUS, apiMethodDoc.getAuth().getRoles().get(0));
			}
			
			if (apiMethodDoc.getPath().contains("/undefinedAuthWithoutAuthOnClass")) {
				Assert.assertNull(apiMethodDoc.getAuth());
			}
			
		}
		
		classes.clear();
		classes.add(TestOldStyleServlets.class);
		apiDoc = jsondocScanner.getApiDocs(classes, MethodDisplay.URI).iterator().next();
		Assert.assertEquals("test-old-style-servlets", apiDoc.getName());
		
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/oldStyle")) {
				Assert.assertEquals(1, apiMethodDoc.getPathparameters().size());
			}
			
			if (apiMethodDoc.getPath().contains("/oldStyleWithList")) {
				Assert.assertEquals(1, apiMethodDoc.getPathparameters().size());
			}
			
			if (apiMethodDoc.getPath().contains("/oldStyleWithMap")) {
				Assert.assertEquals(1, apiMethodDoc.getPathparameters().size());
			}
			
			if (apiMethodDoc.getPath().contains("/oldStyleMixed")) {
				Assert.assertEquals(3, apiMethodDoc.getPathparameters().size());
				Assert.assertEquals(1, apiMethodDoc.getQueryparameters().size());
				Assert.assertEquals("qTest", apiMethodDoc.getQueryparameters().iterator().next().getDefaultvalue());
			}
			
			if (apiMethodDoc.getPath().contains("/oldStyleResponseObject")) {
				Assert.assertEquals("list", apiMethodDoc.getResponse().getJsondocType().getOneLineText());
			}
			
			if (apiMethodDoc.getPath().contains("/oldStyleBodyObject")) {
				Assert.assertEquals("list", apiMethodDoc.getBodyobject().getJsondocType().getOneLineText());
			}
		}
		
		classes.clear();
		classes.add(TestErrorsAndWarningsAndHints.class);
		apiDoc = jsondocScanner.getApiDocs(classes, MethodDisplay.URI).iterator().next();
		Assert.assertEquals("test-errors-warnings-hints", apiDoc.getName());
		ApiMethodDoc apiMethodDoc = apiDoc.getMethods().iterator().next();
		Assert.assertEquals(1, apiMethodDoc.getJsondocerrors().size());
		Assert.assertEquals(1, apiMethodDoc.getJsondocwarnings().size());
		Assert.assertEquals(2, apiMethodDoc.getJsondochints().size());
		
		classes.clear();
		classes.add(TestErrorsAndWarningsAndHintsMethodSummary.class);
		apiDoc = jsondocScanner.getApiDocs(classes, MethodDisplay.SUMMARY).iterator().next();
		apiMethodDoc = apiDoc.getMethods().iterator().next();
		Assert.assertEquals(1, apiMethodDoc.getJsondocerrors().size());
		Assert.assertEquals(1, apiMethodDoc.getJsondocwarnings().size());
		Assert.assertEquals(3, apiMethodDoc.getJsondochints().size());
		
		classes.clear();
		classes.add(InterfaceController.class);
		apiDoc = jsondocScanner.getApiDocs(classes, MethodDisplay.URI).iterator().next();
		Assert.assertEquals("interface-controller", apiDoc.getName());
		apiMethodDoc = apiDoc.getMethods().iterator().next();
		Assert.assertNotNull(apiMethodDoc);
		Assert.assertEquals("/interface", apiMethodDoc.getPath().iterator().next());
		
		classes.clear();
		classes.add(TestDeclaredMethods.class);
		apiDoc = jsondocScanner.getApiDocs(classes, MethodDisplay.URI).iterator().next();
		Assert.assertEquals("test-declared-methods", apiDoc.getName());
		Assert.assertEquals(2, apiDoc.getMethods().size());
		
		
		classes.clear();
		classes.add(TestMultipleParamsWithSameMethod.class);
		apiDoc = jsondocScanner.getApiDocs(classes, MethodDisplay.URI).iterator().next();
		Assert.assertEquals(3, apiDoc.getMethods().size());
		
	}

}