class DummyClass_91381 {
    @Test
    public void testCTor() {
        Element e = new Element("root");
        Element segment = new Element("segment");
        segment.setAttribute("space","5");
        segment.setAttribute("origin","0");
        e.addContent(segment);
        Element group = new Element("group");
        group.setAttribute("offset","0");
        Element fm = new Element("function");
        Element fmn = new Element("name");
        fmn.addContent("F1");
        fm.addContent(fmn);
        fm.setAttribute("size","1");
        fm.setAttribute("kind","momentary");
        group.addContent(fm);
        Element ft = new Element("function");
        Element ftn = new Element("name");
        ftn.addContent("F2");
        ft.addContent(ftn);
        ft.setAttribute("size","1");
        ft.setAttribute("kind","toggle");
        group.addContent(ft);
        Element fa = new Element("function");
        Element fan = new Element("name");
        fan.addContent("F3");
        fa.addContent(fan);
        fa.setAttribute("size","1");
        fa.setAttribute("kind","analog");
        group.addContent(fa);
        e.addContent(group);
        FdiParser t = new FdiParser(e);
        Assert.assertNotNull("exists",t);
    }

}