class DummyClass_112670 {
    @Test
    public void catchesInvalidMessageExceptions() throws IOException {
        String nmeaStream =
            "!AIVDM,1,1,,B,402=481uaUcf;OQ55JS9ITi025Jp,0*2B\n" +
            "!AIVDM,1,1,,B,58LAM242B9POUKWWW<0a>0<4E<58,0*6E\n" +  // invalid
            "!AIVDM,1,1,,A,33nr7t001f13KNTOahh2@QpF00vh,0*58\n";

        InputStream inputStream = new ByteArrayInputStream(nmeaStream.getBytes(StandardCharsets.UTF_8));

        new NMEAMessageInputStreamReader(inputStream, nmeaMessageHandler).run();
    }

}