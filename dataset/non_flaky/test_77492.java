class DummyClass_77492 {
    @Test
        public CustomProperty deserialize(JsonParser parser, DeserializationContext context) throws IOException {
            assertThat(parser.getCodec()).isNotNull();

            TreeNode treeNode = parser.readValueAsTree();
            final TextNode custom = (TextNode) treeNode.path("custom");
            return new CustomProperty(custom.asText());
        }

}