class DummyClass_112643 {
    @Test
    public void canDecodeDac200Fi10InlandShipStaticAndVoyageRelatedData1() {
        AISMessage aisMessage = AISMessage.create(NMEAMessage.fromString("!AIVDM,1,1,,B,839udkPj2d<dteLMt1T0a?bP01L0,0*79"));
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

        assertEquals("02325170", inlandMessage.getUniqueEuropeanVesselIdentificationNumber());
        assertEquals(Float.valueOf(80.0f), inlandMessage.getLengthOfShip());
        assertEquals(Float.valueOf(8.2f), inlandMessage.getBeamOfShip());
        assertEquals(Integer.valueOf(8020), inlandMessage.getShipOrCombinationType());
        assertEquals(Integer.valueOf(0), inlandMessage.getHazardousCargo());
        assertEquals(Float.valueOf(0.0f), inlandMessage.getDraught());
        assertEquals(Integer.valueOf(2), inlandMessage.getLoaded());
        assertEquals(Integer.valueOf(1), inlandMessage.getQualityOfSpeedInformation());
        assertEquals(Integer.valueOf(1), inlandMessage.getQualityOfCourseInformation());
        assertEquals(Integer.valueOf(1), inlandMessage.getQualityOfHeadingInformation());
    }

}