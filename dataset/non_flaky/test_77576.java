class DummyClass_77576 {
    @Test
        public Response streamForever() {
            final StreamingOutput output = os -> {
                //noinspection InfiniteLoopStatement
                while (true) {
                    os.write('a');
                    os.flush();
                }
            };

            return Response.ok(output).build();
        }

}