class DummyClass_19466 {
	@Test public void testGetByEObject_01() throws Exception {
		public EObject getEObjectOrProxy() {
			EObject element = super.getEObjectOrProxy();
			InternalEObject result = (InternalEObject) EcoreFactory.eINSTANCE.create(element.eClass());
			result.eSetProxyURI(EcoreUtil.getURI(element));
			return result;
		}

}