class DummyClass_112635 {
    @Test
    public void digest() throws NoSuchAlgorithmException {
        String expectedDigest = "2ca6350a33d7b19f0ef49799aa96dd61da9e081e";

        AISMessage aisMessage = AISMessage.create(
            NMEAMessage.fromString("!AIVDM,2,1,0,B,539S:k40000000c3G04PPh63<00000000080000o1PVG2uGD:00000000000,0*34"),
            NMEAMessage.fromString("!AIVDM,2,2,0,B,00000000000,2*27")
        );
        byte[] digest = aisMessage.digest();
        String digestAsString = String.format("%040x", new java.math.BigInteger(1, digest));
        assertEquals(expectedDigest, digestAsString);

        // Change line 1
        aisMessage = AISMessage.create(
            NMEAMessage.fromString("!AIVDM,2,1,0,B,539S:k40000000c3G04PPh63<00000000080000o1PVG2uGD:00000000001,0*34"),
            NMEAMessage.fromString("!AIVDM,2,2,0,B,00000000000,2*27")
        );
        digest = aisMessage.digest();
        digestAsString = String.format("%040x", new java.math.BigInteger(1, digest));
        assertNotEquals(expectedDigest, digestAsString);

        // Change line 2
        aisMessage = AISMessage.create(
            NMEAMessage.fromString("!AIVDM,2,1,0,B,539S:k40000000c3G04PPh63<00000000080000o1PVG2uGD:00000000000,0*34"),
            NMEAMessage.fromString("!AIVDM,2,2,0,B,00000000001,2*27")
        );
        digest = aisMessage.digest();
        digestAsString = String.format("%040x", new java.math.BigInteger(1, digest));
        assertNotEquals(expectedDigest, digestAsString);


    }

}