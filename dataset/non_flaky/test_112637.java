class DummyClass_112637 {
    @Test
    public void canDecodeAsmNumberOfPersonsOnboard() {
        AISMessage aisMessage = AISMessage.create(NMEAMessage.fromString("!AIVDM,1,1,,B,63bump80OEGr06P060,4*79"));
        System.out.println(aisMessage.toString());

        assertTrue(aisMessage instanceof AddressedBinaryMessage);
        AddressedBinaryMessage addressedBinaryMessage = (AddressedBinaryMessage) aisMessage;
        assertEquals(1, addressedBinaryMessage.getDesignatedAreaCode().intValue());
        assertEquals(40, addressedBinaryMessage.getFunctionalId().intValue());

        ApplicationSpecificMessage asm = addressedBinaryMessage.getApplicationSpecificMessage();
        assertEquals(1, asm.getDesignatedAreaCode());
        assertEquals(40, asm.getFunctionalId());

        assertTrue(asm instanceof NumberOfPersonsOnBoard);
        NumberOfPersonsOnBoard numberOfPersonsOnBoard = (NumberOfPersonsOnBoard) asm;
        assertEquals("0000000000011000", numberOfPersonsOnBoard.getBinaryData());
        assertEquals(Integer.valueOf(3), numberOfPersonsOnBoard.getNumberOfPersons());
   }

}