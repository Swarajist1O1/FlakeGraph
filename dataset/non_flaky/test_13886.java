class DummyClass_13886 {
    @Test
    public void shouldGenerateNodesAndRelationshipsWithProperties() throws Exception
    {
        // given
        Configuration.Builder config = Configuration.builder();
        config.setValue( DataGenerator.node_count, 5 );
        config.setValue( DataGenerator.relationships, asList( new RelationshipSpec( "FOO", 1 ),
                                                              new RelationshipSpec( "BAR", 2 ) ) );
        config.setValue( DataGenerator.node_properties,
                asList( new PropertySpec( PropertyGenerator.STRING, 2 ) ) );
        config.setValue( DataGenerator.relationship_properties,
                asList( new PropertySpec( PropertyGenerator.STRING, 1 ) ) );

        DataGenerator generator = new DataGenerator( config.build() );

        BatchInserter batchInserter = mock( BatchInserter.class );

        // when
        generator.generateData( batchInserter );

        // then
        verify( batchInserter, times( 5 ) ).createNode( argThat( hasSize( 2 ) ) );
        verify( batchInserter, times( 5 ) ).createRelationship( anyLong(), anyLong(), argThat( hasName( "FOO" ) ),
                                                                argThat( hasSize( 1 ) ) );
        verify( batchInserter, times( 10 ) )
                .createRelationship( anyLong(), anyLong(), argThat( hasName( "BAR" ) ), argThat( hasSize( 1 ) ) );
        verifyNoMoreInteractions( batchInserter );
    }

}