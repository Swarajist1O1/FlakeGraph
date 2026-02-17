class DummyClass_19437 {
	@Test public void testNegatedTokenNotEOF_2() throws Exception {
		String grammarAsText =
				"grammar test with org.eclipse.xtext.common.Terminals\n" +
						"generate test 'http://test'\n" +
						"A: foo=DUMMY;\n" +
						"terminal DUMMY: !(EOF | ID);";
		Grammar grammar = (Grammar) getModel(grammarAsText);
		XtextValidator validator = get(XtextValidator.class);
		ValidatingMessageAcceptor messageAcceptor = new ValidatingMessageAcceptor(null, true, false);
		TerminalRule terminal = (TerminalRule) grammar.getRules().get(1);
		NegatedToken token = (NegatedToken)terminal.getAlternatives();
		messageAcceptor.expectedContext(((Alternatives)token.getTerminal()).getElements().get(0));
		configureValidator(validator, messageAcceptor, token);
		validator.checkNegatedTokenNotEOF(token);
		messageAcceptor.validate();
	}

	public class ValidatingMessageAcceptor extends AbstractValidationMessageAcceptor {

		private final Set<EObject> contexts;
		private boolean error;
		private boolean warning;
		private boolean info;

		public ValidatingMessageAcceptor(EObject context, boolean error, boolean warning) {
			this.contexts = Sets.newHashSet();
			if (context != null)
				contexts.add(context);
			this.error = error;
			this.warning = warning;
		}
		
		public void expectedContext(EObject... contexts) {
			this.contexts.addAll(Arrays.asList(contexts));
		}

}