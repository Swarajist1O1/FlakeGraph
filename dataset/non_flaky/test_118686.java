class DummyClass_118686 {
    @Test
    public void shouldDecodeXmlHeader() {
        Object temp;

        write(XML3);

        temp = channel.readInbound();
        assertThat(temp, instanceOf(XmlDocumentStart.class));
        assertThat(((XmlDocumentStart) temp).version(), is("1.1"));
        assertThat(((XmlDocumentStart) temp).encoding(), is("UTF-8"));
        assertThat(((XmlDocumentStart) temp).standalone(), is(true));
        assertThat(((XmlDocumentStart) temp).encodingScheme(), is("UTF-8"));

        temp = channel.readInbound();
        assertThat(temp, instanceOf(XmlElementStart.class));
        assertThat(((XmlElementStart) temp).name(), is("netty"));
        assertThat(((XmlElementStart) temp).prefix(), is(""));
        assertThat(((XmlElementStart) temp).namespace(), is(""));
        assertThat(((XmlElementStart) temp).attributes().size(), is(0));
        assertThat(((XmlElementStart) temp).namespaces().size(), is(0));

        temp = channel.readInbound();
        assertThat(temp, instanceOf(XmlElementEnd.class));
        assertThat(((XmlElementEnd) temp).name(), is("netty"));
        assertThat(((XmlElementEnd) temp).prefix(), is(""));
        assertThat(((XmlElementEnd) temp).namespace(), is(""));
        assertThat(((XmlElementEnd) temp).namespaces().size(), is(0));

        temp = channel.readInbound();
        assertThat(temp, nullValue());
    }

}