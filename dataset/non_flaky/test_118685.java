class DummyClass_118685 {
    @Test
    public void shouldDecodeRequestWithSimpleXml() {
        Object temp;

        write(XML1);

        temp = channel.readInbound();
        assertThat(temp, instanceOf(XmlDocumentStart.class));
        assertThat(((XmlDocumentStart) temp).version(), is("1.0"));
        assertThat(((XmlDocumentStart) temp).encoding(), is("UTF-8"));
        assertThat(((XmlDocumentStart) temp).standalone(), is(false));
        assertThat(((XmlDocumentStart) temp).encodingScheme(), is(nullValue()));

        temp = channel.readInbound();
        assertThat(temp, instanceOf(XmlDTD.class));
        assertThat(((XmlDTD) temp).text(), is("employee.dtd"));

        temp = channel.readInbound();
        assertThat(temp, instanceOf(XmlProcessingInstruction.class));
        assertThat(((XmlProcessingInstruction) temp).target(), is("xml-stylesheet"));
        assertThat(((XmlProcessingInstruction) temp).data(), is("type=\"text/css\" href=\"netty.css\""));

        temp = channel.readInbound();
        assertThat(temp, instanceOf(XmlProcessingInstruction.class));
        assertThat(((XmlProcessingInstruction) temp).target(), is("xml-test"));
        assertThat(((XmlProcessingInstruction) temp).data(), is(""));

        temp = channel.readInbound();
        assertThat(temp, instanceOf(XmlElementStart.class));
        assertThat(((XmlElementStart) temp).name(), is("employee"));
        assertThat(((XmlElementStart) temp).prefix(), is(""));
        assertThat(((XmlElementStart) temp).namespace(), is(""));
        assertThat(((XmlElementStart) temp).attributes().size(), is(0));
        assertThat(((XmlElementStart) temp).namespaces().size(), is(1));
        assertThat(((XmlElementStart) temp).namespaces().get(0).prefix(), is("nettya"));
        assertThat(((XmlElementStart) temp).namespaces().get(0).uri(), is("http://netty.io/netty/a"));

        temp = channel.readInbound();
        assertThat(temp, instanceOf(XmlElementStart.class));
        assertThat(((XmlElementStart) temp).name(), is("id"));
        assertThat(((XmlElementStart) temp).prefix(), is("nettya"));
        assertThat(((XmlElementStart) temp).namespace(), is("http://netty.io/netty/a"));
        assertThat(((XmlElementStart) temp).attributes().size(), is(0));
        assertThat(((XmlElementStart) temp).namespaces().size(), is(0));

        temp = channel.readInbound();
        assertThat(temp, instanceOf(XmlEntityReference.class));
        assertThat(((XmlEntityReference) temp).name(), is("plusmn"));
        assertThat(((XmlEntityReference) temp).text(), is(""));

        temp = channel.readInbound();
        assertThat(temp, instanceOf(XmlCharacters.class));
        assertThat(((XmlCharacters) temp).data(), is("1"));

        temp = channel.readInbound();
        assertThat(temp, instanceOf(XmlElementEnd.class));
        assertThat(((XmlElementEnd) temp).name(), is("id"));
        assertThat(((XmlElementEnd) temp).prefix(), is("nettya"));
        assertThat(((XmlElementEnd) temp).namespace(), is("http://netty.io/netty/a"));

        temp = channel.readInbound();
        assertThat(temp, instanceOf(XmlCharacters.class));
        assertThat(((XmlCharacters) temp).data(), is("\n"));

        temp = channel.readInbound();
        assertThat(temp, nullValue());

        write(XML2);

        temp = channel.readInbound();
        assertThat(temp, instanceOf(XmlElementStart.class));
        assertThat(((XmlElementStart) temp).name(), is("name"));
        assertThat(((XmlElementStart) temp).prefix(), is(""));
        assertThat(((XmlElementStart) temp).namespace(), is(""));
        assertThat(((XmlElementStart) temp).attributes().size(), is(1));
        assertThat(((XmlElementStart) temp).attributes().get(0).name(), is("type"));
        assertThat(((XmlElementStart) temp).attributes().get(0).value(), is("given"));
        assertThat(((XmlElementStart) temp).attributes().get(0).prefix(), is(""));
        assertThat(((XmlElementStart) temp).attributes().get(0).namespace(), is(""));
        assertThat(((XmlElementStart) temp).namespaces().size(), is(0));

        temp = channel.readInbound();
        assertThat(temp, instanceOf(XmlCharacters.class));
        assertThat(((XmlCharacters) temp).data(), is("Alba"));

        temp = channel.readInbound();
        assertThat(temp, instanceOf(XmlElementEnd.class));
        assertThat(((XmlElementEnd) temp).name(), is("name"));
        assertThat(((XmlElementEnd) temp).prefix(), is(""));
        assertThat(((XmlElementEnd) temp).namespace(), is(""));

        temp = channel.readInbound();
        assertThat(temp, instanceOf(XmlCdata.class));
        assertThat(((XmlCdata) temp).data(), is(" <some data &gt;/> "));

        temp = channel.readInbound();
        assertThat(temp, instanceOf(XmlCharacters.class));
        assertThat(((XmlCharacters) temp).data(), is("   "));

        temp = channel.readInbound();
        assertThat(temp, instanceOf(XmlComment.class));
        assertThat(((XmlComment) temp).data(), is(" namespaced "));

        temp = channel.readInbound();
        assertThat(temp, instanceOf(XmlElementStart.class));
        assertThat(((XmlElementStart) temp).name(), is("salary"));
        assertThat(((XmlElementStart) temp).prefix(), is("nettyb"));
        assertThat(((XmlElementStart) temp).namespace(), is("http://netty.io/netty/b"));
        assertThat(((XmlElementStart) temp).attributes().size(), is(1));
        assertThat(((XmlElementStart) temp).attributes().get(0).name(), is("period"));
        assertThat(((XmlElementStart) temp).attributes().get(0).value(), is("weekly"));
        assertThat(((XmlElementStart) temp).attributes().get(0).prefix(), is("nettyb"));
        assertThat(((XmlElementStart) temp).attributes().get(0).namespace(), is("http://netty.io/netty/b"));
        assertThat(((XmlElementStart) temp).namespaces().size(), is(1));
        assertThat(((XmlElementStart) temp).namespaces().get(0).prefix(), is("nettyb"));
        assertThat(((XmlElementStart) temp).namespaces().get(0).uri(), is("http://netty.io/netty/b"));

        temp = channel.readInbound();
        assertThat(temp, instanceOf(XmlCharacters.class));
        assertThat(((XmlCharacters) temp).data(), is("100"));

        temp = channel.readInbound();
        assertThat(temp, instanceOf(XmlElementEnd.class));
        assertThat(((XmlElementEnd) temp).name(), is("salary"));
        assertThat(((XmlElementEnd) temp).prefix(), is("nettyb"));
        assertThat(((XmlElementEnd) temp).namespace(), is("http://netty.io/netty/b"));
        assertThat(((XmlElementEnd) temp).namespaces().size(), is(1));
        assertThat(((XmlElementEnd) temp).namespaces().get(0).prefix(), is("nettyb"));
        assertThat(((XmlElementEnd) temp).namespaces().get(0).uri(), is("http://netty.io/netty/b"));

        temp = channel.readInbound();
        assertThat(temp, instanceOf(XmlElementStart.class));
        assertThat(((XmlElementStart) temp).name(), is("last"));
        assertThat(((XmlElementStart) temp).prefix(), is(""));
        assertThat(((XmlElementStart) temp).namespace(), is(""));
        assertThat(((XmlElementStart) temp).attributes().size(), is(0));
        assertThat(((XmlElementStart) temp).namespaces().size(), is(0));

        temp = channel.readInbound();
        assertThat(temp, instanceOf(XmlElementEnd.class));
        assertThat(((XmlElementEnd) temp).name(), is("last"));
        assertThat(((XmlElementEnd) temp).prefix(), is(""));
        assertThat(((XmlElementEnd) temp).namespace(), is(""));
        assertThat(((XmlElementEnd) temp).namespaces().size(), is(0));

        temp = channel.readInbound();
        assertThat(temp, instanceOf(XmlElementEnd.class));
        assertThat(((XmlElementEnd) temp).name(), is("employee"));
        assertThat(((XmlElementEnd) temp).prefix(), is(""));
        assertThat(((XmlElementEnd) temp).namespace(), is(""));
        assertThat(((XmlElementEnd) temp).namespaces().size(), is(1));
        assertThat(((XmlElementEnd) temp).namespaces().get(0).prefix(), is("nettya"));
        assertThat(((XmlElementEnd) temp).namespaces().get(0).uri(), is("http://netty.io/netty/a"));

        temp = channel.readInbound();
        assertThat(temp, nullValue());
    }

}