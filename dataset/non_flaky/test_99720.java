class DummyClass_99720 {
    @Test
    public void isSerializable() throws Exception
    {
        Map<String, String[]> args = new HashMap<>();
        args.put("write", new String[] {});
        StressSettings settings = StressSettings.get(args);
        // Will throw if not all settings are Serializable
        new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(settings);
    }

}