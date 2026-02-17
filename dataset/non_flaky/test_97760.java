class DummyClass_97760 {
    @Test
    public void testReturnedTypesFromResource() {
        JaxrsApplicationParser jaxrsApplicationParser = createJaxrsApplicationParser(TestUtils.settings());
        final JaxrsApplicationParser.Result result = jaxrsApplicationParser.tryParse(new SourceType<>(TestResource1.class));
        Assert.assertNotNull(result);
        List<Type> types = getTypes(result.discoveredTypes);
        final List<Type> expectedTypes = Arrays.asList(
                A.class,
                new TypeReference<List<B>>(){}.getType(),
                C.class,
                new TypeReference<List<D>>(){}.getType(),
                List.class,
                E.class,
                new TypeReference<List<F>>(){}.getType(),
                G.class,
                new TypeReference<Map<String, H>>(){}.getType(),
                I.class,
                JGenericArrayType.of(J[].class),
                // types handled by DefaultTypeProcessor
                String.class, Boolean.class, Character.class, Number.class, Integer.class, int.class, void.class
        );
        assertHasSameItems(expectedTypes, types);
    }

}