class DummyClass_113747 {
@Test(timeOut = 3000)
    public Publisher<Message> createPublisher(long elements) {
        ReactorTckGrpc.ReactorTckStub stub = ReactorTckGrpc.newReactorStub(channel);
        Mono<Message> request = Mono.just(toMessage((int) elements));
        Publisher<Message> publisher = stub.oneToMany(request).publishOn(Schedulers.immediate());

        return publisher;
    }

}