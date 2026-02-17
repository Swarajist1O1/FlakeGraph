class DummyClass_98216 {
    @Test
    public void testDoubleOutput() throws Exception {
        StringWriter writer = new StringWriter();
        JsonWriter jsonWriter = new JsonWriter(writer);

        Node parent = createMock(Node.class);
        Property doubleProperty = createMock(Property.class);
        Value doublePropertyValue = createMock(Value.class);
        expect(doubleProperty.getType()).andReturn(PropertyType.DOUBLE).anyTimes();
        expect(doubleProperty.getName()).andReturn("singleValued").anyTimes();
        expect(doubleProperty.isMultiple()).andReturn(false).anyTimes();
        expect(doubleProperty.getValue()).andReturn(doublePropertyValue).anyTimes();
        expect(doublePropertyValue.getType()).andReturn(PropertyType.DOUBLE).anyTimes();
        expect(doublePropertyValue.getDouble()).andReturn(5d).anyTimes();
        expect(doublePropertyValue.getString()).andReturn("5").anyTimes();

        Property mvDoubleProperty = createMock(Property.class);
        Value mvDoublePropertyValue1 = createMock(Value.class);
        Value mvDoublePropertyValue2 = createMock(Value.class);
        expect(mvDoubleProperty.getType()).andReturn(PropertyType.DOUBLE).anyTimes();
        expect(mvDoubleProperty.getName()).andReturn("multiValued").anyTimes();
        expect(mvDoubleProperty.isMultiple()).andReturn(true).anyTimes();
        expect(mvDoubleProperty.getValues()).andReturn(new Value[] { mvDoublePropertyValue1, mvDoublePropertyValue2}).anyTimes();
        expect(mvDoublePropertyValue1.getType()).andReturn(PropertyType.DOUBLE).anyTimes();
        expect(mvDoublePropertyValue1.getDouble()).andReturn(42d).anyTimes();
        expect(mvDoublePropertyValue1.getString()).andReturn("42").anyTimes();
        expect(mvDoublePropertyValue2.getType()).andReturn(PropertyType.DOUBLE).anyTimes();
        expect(mvDoublePropertyValue2.getDouble()).andReturn(98.6).anyTimes();
        expect(mvDoublePropertyValue2.getString()).andReturn("98.6").anyTimes();

        final List<Property> properties = new ArrayList<Property>();
        properties.add(doubleProperty);
        properties.add(mvDoubleProperty);
        expect(parent.getProperties()).andAnswer(new IAnswer<PropertyIterator>() {
            @Override
            public PropertyIterator answer() throws Throwable {
                return new PropertyIteratorAdapter(properties.iterator());
            }

}