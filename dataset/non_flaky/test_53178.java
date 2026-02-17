class DummyClass_53178 {
    @Test
    public void testFormatting() throws IOException, ParseException {
        Date now = new Date();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        JsonGenerator gen = new JsonFactory().createGenerator(bos);
        new JsonDateSerializer().serialize(now, gen, null);
        gen.close();
        Assert.assertEquals(String.format("\"%s\"", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(now)),
                bos.toString());
    }

}