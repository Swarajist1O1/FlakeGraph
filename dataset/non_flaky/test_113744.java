class DummyClass_113744 {
@Test(timeOut = 3000)
    public Publisher<Message> createPublisher(long elements) {
        ReactorTckGrpc.ReactorTckStub stub = ReactorTckGrpc.newReactorStub(channel);
        Flux<Message> request = Flux.range(0, (int)elements).map(this::toMessage);
        Publisher<Message> publisher = stub.manyToMany(request).publishOn(Schedulers.immediate());

        return publisher;
    }

}