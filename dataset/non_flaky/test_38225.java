class DummyClass_38225 {
    @Test
    public void testHexConverter(){
        byte[] bytes = new byte[]{(byte)255, (byte)255, 0, 0};
        System.out.println(Arrays.toString(bytes) +" -> 0x" + TextUtils.byteArrayToHexString(bytes));
        assertEquals("ffff0000", TextUtils.byteArrayToHexString(bytes));
    }

}