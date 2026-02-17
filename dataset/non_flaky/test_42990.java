class DummyClass_42990 {
	@Test
	public void testReflex() throws NoSuchMethodException, SecurityException, ClassNotFoundException, JsonGenerationException, JsonMappingException, IOException {
		mapper.setSerializationInclusion(Include.NON_NULL);
		JSONDocType jsonDocType = new JSONDocType();
		
		Method method = JSONDocTypeBuilderTest.class.getMethod("getString");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assert.assertEquals("string", jsonDocType.getOneLineText());
		System.out.println("---------------------------");
		
		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getInteger");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assert.assertEquals("integer", jsonDocType.getOneLineText());
		System.out.println("---------------------------");
		
		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getInt");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assert.assertEquals("int", jsonDocType.getOneLineText());
		System.out.println("---------------------------");
		
		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getLong");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assert.assertEquals("long", jsonDocType.getOneLineText());
		System.out.println("---------------------------");
		
		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getlong");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assert.assertEquals("long", jsonDocType.getOneLineText());
		System.out.println("---------------------------");
		
		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getListString");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assert.assertEquals("list of string", jsonDocType.getOneLineText());
		System.out.println("---------------------------");
		
		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getListSetString");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assert.assertEquals("list of set of string", jsonDocType.getOneLineText());
		System.out.println("---------------------------");
		
		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getStringArray");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assert.assertEquals("array of string", jsonDocType.getOneLineText());
		System.out.println("---------------------------");

		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getIntegerArray");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assert.assertEquals("array of integer", jsonDocType.getOneLineText());
		System.out.println("---------------------------");
		
		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getListOfStringArray");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assert.assertEquals("array of list of string", jsonDocType.getOneLineText());
		System.out.println("---------------------------");
		
		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getSetOfStringArray");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assert.assertEquals("array of set of string", jsonDocType.getOneLineText());
		System.out.println("---------------------------");
		
		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getList");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assert.assertEquals("list", jsonDocType.getOneLineText());
		System.out.println("---------------------------");
		
		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getListOfWildcard");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assert.assertEquals("list of wildcard", jsonDocType.getOneLineText());
		System.out.println("---------------------------");

		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getListOfWildcardArray");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assert.assertEquals("array of list of wildcard", jsonDocType.getOneLineText());
		System.out.println("---------------------------");
		
		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getListArray");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assert.assertEquals("array of list", jsonDocType.getOneLineText());
		System.out.println("---------------------------");

		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getSetArray");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assert.assertEquals("array of set", jsonDocType.getOneLineText());
		System.out.println("---------------------------");
		
		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getMap");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assert.assertEquals("map", jsonDocType.getOneLineText());
		System.out.println("---------------------------");
		
		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getHashMap");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assert.assertEquals("hashmap", jsonDocType.getOneLineText());
		System.out.println("---------------------------");

		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getMapStringInteger");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assert.assertEquals("map[string, integer]", jsonDocType.getOneLineText());
		System.out.println("---------------------------");

		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getMapListOfStringInteger");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assert.assertEquals("map[list of string, integer]", jsonDocType.getOneLineText());
		System.out.println("---------------------------");

		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getMapStringSetOfInteger");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assert.assertEquals("map[string, set of integer]", jsonDocType.getOneLineText());
		System.out.println("---------------------------");

		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getMapListOfStringSetOfInteger");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assert.assertEquals("map[list of string, set of integer]", jsonDocType.getOneLineText());
		System.out.println("---------------------------");

		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getMapListOfSetOfStringSetOfInteger");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assert.assertEquals("map[list of set of string, set of integer]", jsonDocType.getOneLineText());
		System.out.println("---------------------------");

		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getMapWildcardInteger");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assert.assertEquals("map[wildcard, integer]", jsonDocType.getOneLineText());
		System.out.println("---------------------------");

		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getMapWildcardWildcard");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assert.assertEquals("map[wildcard, wildcard]", jsonDocType.getOneLineText());
		System.out.println("---------------------------");

		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getMapListOfWildcardWildcard");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assert.assertEquals("map[list of wildcard, wildcard]", jsonDocType.getOneLineText());
		System.out.println("---------------------------");

		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getMapMapInteger");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assert.assertEquals("map[map, integer]", jsonDocType.getOneLineText());
		System.out.println("---------------------------");

		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getMapMapStringLongInteger");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assert.assertEquals("map[map[string, long], integer]", jsonDocType.getOneLineText());
		System.out.println("---------------------------");

		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getResponseEntityString");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assert.assertEquals("responseentity of string", jsonDocType.getOneLineText());
		System.out.println("---------------------------");

		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getResponseEntityListOfString");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assert.assertEquals("responseentity of list of string", jsonDocType.getOneLineText());
		System.out.println("---------------------------");
		
		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getParentPojoList");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assert.assertEquals("list of my_parent", jsonDocType.getOneLineText());
		System.out.println("---------------------------");
		
		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getSpecializedWGenericsPojo");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assert.assertEquals("fooPojo of T", jsonDocType.getOneLineText());
		System.out.println("---------------------------");		
	}

}