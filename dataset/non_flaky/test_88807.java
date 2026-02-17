class DummyClass_88807 {
    @Test
    public void testBinaryObjectApi() throws Exception {
        try (Ignite srv = Ignition.start(Config.getServerConfiguration())) {
            try (IgniteClient client = Ignition.startClient(new ClientConfiguration().setAddresses(Config.SERVER))) {
                // Use "server-side" IgniteBinary as a reference to test the thin client IgniteBinary against
                IgniteBinary refBinary = srv.binary();

                IgniteBinary binary = client.binary();

                Person obj = new Person(1, "Joe");

                int refTypeId = refBinary.typeId(Person.class.getName());
                int typeId = binary.typeId(Person.class.getName());

                assertEquals(refTypeId, typeId);

                BinaryObject refBinObj = refBinary.toBinary(obj);
                BinaryObject binObj = binary.toBinary(obj);

                assertBinaryObjectsEqual(refBinObj, binObj);

                assertBinaryTypesEqual(refBinary.type(typeId), binary.type(typeId));

                assertBinaryTypesEqual(refBinary.type(Person.class), binary.type(Person.class));

                assertBinaryTypesEqual(refBinary.type(Person.class.getName()), binary.type(Person.class.getName()));

                Collection<BinaryType> refTypes = refBinary.types();
                Collection<BinaryType> types = binary.types();

                assertEquals(refTypes.size(), types.size());

                BinaryObject refEnm = refBinary.buildEnum(Enum.class.getName(), Enum.DEFAULT.ordinal());
                BinaryObject enm = binary.buildEnum(Enum.class.getName(), Enum.DEFAULT.ordinal());

                assertBinaryObjectsEqual(refEnm, enm);

                Map<String, Integer> enumMap = Arrays.stream(Enum.values())
                    .collect(Collectors.toMap(java.lang.Enum::name, java.lang.Enum::ordinal));

                BinaryType refEnumType = refBinary.registerEnum(Enum.class.getName(), enumMap);
                BinaryType enumType = binary.registerEnum(Enum.class.getName(), enumMap);

                assertBinaryTypesEqual(refEnumType, enumType);

                refEnm = refBinary.buildEnum(Enum.class.getName(), Enum.DEFAULT.name());
                enm = binary.buildEnum(Enum.class.getName(), Enum.DEFAULT.name());

                assertBinaryObjectsEqual(refEnm, enm);
            }
        }
    }

}