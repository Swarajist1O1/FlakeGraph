class DummyClass_112644 {
    @Test
    public void canDecodeDac200Fi10InlandShipStaticAndVoyageRelatedData2() {
        AISMessage aisMessage = AISMessage.create(NMEAMessage.fromString("!AIVDM,1,1,,A,83aDCr@j2P000000029Pt?cm0000,0*5F"));
        System.out.println(aisMessage.toString());

        assertTrue(aisMessage instanceof BinaryBroadcastMessage);
        BinaryBroadcastMessage binaryBroadcastMessage = (BinaryBroadcastMessage) aisMessage;
        assertEquals(200, binaryBroadcastMessage.getDesignatedAreaCode().intValue());
        assertEquals(10, binaryBroadcastMessage.getFunctionalId().intValue());

        ApplicationSpecificMessage asm = binaryBroadcastMessage.getApplicationSpecificMessage();
        assertEquals(200, asm.getDesignatedAreaCode());
        assertEquals(10, asm.getFunctionalId());

        assertTrue(asm instanceof InlandShipStaticAndVoyageRelatedData);
        InlandShipStaticAndVoyageRelatedData inlandMessage = (InlandShipStaticAndVoyageRelatedData) asm;

        assertEquals("", inlandMessage.getUniqueEuropeanVesselIdentificationNumber());
        assertEquals(Float.valueOf(110.0f), inlandMessage.getLengthOfShip());
        assertEquals(Float.valueOf(12.0f), inlandMessage.getBeamOfShip());
        assertEquals(Integer.valueOf(8030), inlandMessage.getShipOrCombinationType());
        assertEquals(Integer.valueOf(5), inlandMessage.getHazardousCargo());
        assertEquals(Float.valueOf(0.0f), inlandMessage.getDraught());
        assertEquals(Integer.valueOf(0), inlandMessage.getLoaded());
        assertEquals(Integer.valueOf(0), inlandMessage.getQualityOfSpeedInformation());
        assertEquals(Integer.valueOf(0), inlandMessage.getQualityOfCourseInformation());
        assertEquals(Integer.valueOf(0), inlandMessage.getQualityOfHeadingInformation());
    }

}