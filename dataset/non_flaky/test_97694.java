class DummyClass_97694 {
    @Test
    public void testIdClass() {
        final Settings settings = TestUtils.settings();
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(Car.class));
        final String expected = (
                "\n" +
                "interface Car {\n" +
                "    '@class': 'cz.habarta.typescript.generator.TaggedUnionsTest$DieselCar' | 'cz.habarta.typescript.generator.TaggedUnionsTest$ElectricCar';\n" +
                "    name: string;\n" +
                "}\n" +
                "\n" +
                "interface DieselCar extends Car {\n" +
                "    '@class': 'cz.habarta.typescript.generator.TaggedUnionsTest$DieselCar';\n" +
                "    fuelTankCapacityInLiters: number;\n" +
                "}\n" +
                "\n" +
                "interface ElectricCar extends Car {\n" +
                "    '@class': 'cz.habarta.typescript.generator.TaggedUnionsTest$ElectricCar';\n" +
                "    batteryCapacityInKWh: number;\n" +
                "}\n" +
                "\n" +
                "type CarUnion = DieselCar | ElectricCar;\n" +
                ""
                ).replace('\'', '"');
        Assert.assertEquals(expected, output);
    }

}