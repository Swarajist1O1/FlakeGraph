class DummyClass_112647 {
    @Test
    public void digest() throws NoSuchAlgorithmException {
        AISMessage aisMessage = AISMessage.create(NMEAMessage.fromString("!AIVDM,1,1,,A,34RjBV0028o:pnNEBeU<pJF>0PT@,0*3F"));
        byte[] digest = aisMessage.digest();
        String digestAsString = String.format("%040x", new java.math.BigInteger(1, digest));
        assertEquals("673ac3b20886868cafe7376e05092bf625f00b75", digestAsString);
    }

}