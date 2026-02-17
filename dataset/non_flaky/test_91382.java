class DummyClass_91382 {
    @Test
    public void testReadFromFile() throws Exception {
        FileReader r = new FileReader("test/org/openlcb/implementations/throttle/FdiTestFile.xml");
        Element e = org.openlcb.cdi.jdom.XmlHelper.parseXmlFromReader(r);
        FdiParser t = new FdiParser(e);
        Assert.assertNotNull("exists",t);
    }

}