class DummyClass_118772 {
    @Test
    public void testIsTextWithInvalidIndexAndLength() {
        ByteBuf buffer = Unpooled.buffer();
        try {
            buffer.writeBytes(new byte[4]);
            int[][] validIndexLengthPairs = {
                    {4, 0},
                    {0, 4},
                    {1, 3},
            };
            for (int[] pair : validIndexLengthPairs) {
                assertTrue(ByteBufUtil.isText(buffer, pair[0], pair[1], CharsetUtil.US_ASCII));
            }
            int[][] invalidIndexLengthPairs = {
                    {4, 1},
                    {-1, 2},
                    {3, -1},
                    {3, -2},
                    {5, 0},
                    {1, 5},
            };
            for (int[] pair : invalidIndexLengthPairs) {
                try {
                    ByteBufUtil.isText(buffer, pair[0], pair[1], CharsetUtil.US_ASCII);
                    fail("Expected IndexOutOfBoundsException");
                } catch (IndexOutOfBoundsException e) {
                    // expected
                }
            }
        } finally {
            buffer.release();
        }
    }

}