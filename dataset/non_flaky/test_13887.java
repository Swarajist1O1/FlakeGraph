class DummyClass_13887 {
    @Test
    public void dumpJmxInfo() throws Exception
    {
        List<Triplet<String, String, String>> beanItems = new ArrayList<>();
        AsciiDocListGenerator listGenerator = new AsciiDocListGenerator( "jmx-list", "MBeans exposed by Neo4j", false );

        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        SortedMap<String, ObjectName> neo4jBeans = new TreeMap<String, ObjectName>(
                String.CASE_INSENSITIVE_ORDER );

        for ( String query : QUERIES )
        {
            Set<ObjectInstance> beans = mBeanServer.queryMBeans(
                    new ObjectName( query ), null );
            for ( ObjectInstance bean : beans )
            {
                ObjectName objectName = bean.getObjectName();
                String name = objectName.getKeyProperty( BEAN_NAME );
                if ( EXCLUDES.contains( name ) )
                {
                    continue;
                }
                String name0 = objectName.getKeyProperty( BEAN_NAME0 );
                if ( name0 != null )
                {
                    name += '/' + name0;
                }
                neo4jBeans.put( name, bean.getObjectName() );
            }

        }
        assertEquals( "Sanity checking the number of beans found;",
                EXPECTED_NUMBER_OF_BEANS, neo4jBeans.size() );
        for ( Map.Entry<String, ObjectName> beanEntry : neo4jBeans.entrySet() )
        {
            ObjectName objectName = beanEntry.getValue();
            String name = beanEntry.getKey();
            Set<ObjectInstance> mBeans = mBeanServer.queryMBeans( objectName,
                    null );
            if ( mBeans.size() != 1 )
            {
                throw new IllegalStateException( "Unexpected size ["
                        + mBeans.size()
                        + "] of query result for ["
                        + objectName + "]." );
            }
            ObjectInstance bean = mBeans.iterator()
                    .next();
            MBeanInfo info = mBeanServer.getMBeanInfo( objectName );
            String description = info.getDescription()
                    .replace( '\n', ' ' );

            String id = getId( name );
            beanItems.add( Triplet.of( id, name, description ) );

            writeDetailsToFile( id, objectName, bean, info, description );
        }
        Writer fw = null;
        try
        {
            fw = AsciiDocGenerator.getFW( "target/docs/ops", "JMX List" );
            fw.write( listGenerator.generateListAndTableCombo( beanItems ) );
        }
        finally
        {
            if ( fw != null )
            {
                fw.close();
            }
        }
    }

}