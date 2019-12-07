package com.yang.netty;

/*
 * 【Bootstrap、ServerBootstrap】：
 *      Bootstrap 意思是引导，一个 Netty 应用通常由一个 Bootstrap 开始，主要作用是配置整个 Netty 程序，
 *      串联各个组件，Netty 中 Bootstrap 类是客户端程序的启动引导类，ServerBootstrap 是服务端启动引导类。
 * 【Future、ChannelFuture】：
 *      在 Netty 中所有的 IO 操作都是异步的，不能立刻得知消息是否被正确处理。但是可以过一会等它执行完成或者直接注册一个监听，
 *      具体的实现就是通过 Future 和 ChannelFuture，他们可以注册一个监听，当操作执行成功或失败时监听会自动触发注册的监听事件。
 *      channelFuture.addListener
 * 【Channel】：
 *      Netty 网络通信的组件，能够用于执行网络 I/O 操作。Channel 为用户提供：
 *          1）当前网络连接的通道的状态（例如是否打开？是否已连接？）
 *          2）网络连接的配置参数 （例如接收缓冲区大小）
 *          3）提供异步的网络 I/O 操作(如建立连接，读写，绑定端口)，异步调用意味着任何 I/O 调用都将立即返回，并且不保证在调用结束时所请求的 I/O 操作已完成。
 *          4）调用立即返回一个 ChannelFuture 实例，通过注册监听器到 ChannelFuture 上，可以 I/O 操作成功、失败或取消时回调通知调用方。
 *          5）支持关联 I/O 操作与对应的处理程序。
 *      不同协议、不同的阻塞类型的连接都有不同的 Channel 类型与之对应。
 *      下面是一些常用的 Channel 类型：
 *          NioSocketChannel，异步的客户端 TCP SocketServer 连接。
 *          NioServerSocketChannel，异步的服务器端 TCP SocketServer 连接。
 *          NioDatagramChannel，异步的 UDP 连接。
 *          NioSctpChannel，异步的客户端 Sctp 连接。
 *          NioSctpServerChannel，异步的 Sctp 服务器端连接，这些通道涵盖了 UDP 和 TCP 网络 IO 以及文件 IO。
 * 【Selector】：
 *      Netty 基于 Selector 对象实现 I/O 多路复用，通过 Selector 一个线程可以监听多个连接的 Channel 事件。
 *      当向一个 Selector 中注册 Channel 后，Selector 内部的机制就可以自动不断地查询(Select) 这些注册的 Channel 是否有已就绪的 I/O 事件（例如可读，可写，网络连接完成等），
 *      这样程序就可以很简单地使用一个线程高效地管理多个 Channel 。
 * 【NioEventLoop】：
 *      NioEventLoop 中维护了一个线程和任务队列，支持异步提交执行任务，线程启动时会调用 NioEventLoop 的 run 方法，执行 I/O 任务和非 I/O 任务：
 *          I/O 任务，即 selectionKey 中 ready 的事件，如 accept、connect、read、write 等，由 processSelectedKeys 方法触发。
 *          非 IO 任务，添加到 taskQueue 中的任务，如 register0、bind0 等任务，由 runAllTasks 方法触发。
 *          两种任务的执行时间比由变量 ioRatio 控制，默认为 50，则表示允许非 IO 任务执行的时间与 IO 任务的执行时间相等。
 * 【NioEventLoopGroup】：
 *      NioEventLoopGroup，主要管理 eventLoop 的生命周期，可以理解为一个线程池，内部维护了一组线程，每个线程(NioEventLoop)负责处理多个 Channel 上的事件，
 *      而一个 Channel 只对应于一个线程。
 * 【ChannelHandler】：
 *      ChannelHandler 是一个接口，处理 I/O 事件或拦截 I/O 操作，并将其转发到其 ChannelPipeline(业务处理链)中的下一个处理程序。
 *      ChannelHandler 本身并没有提供很多方法，因为这个接口有许多的方法需要实现，方便使用期间，可以继承它的子类：
 *          ChannelInboundHandler 用于处理入站 I/O 事件。
 *          ChannelOutboundHandler 用于处理出站 I/O 操作。
 *      或者使用以下适配器类：
 *          ChannelInboundHandlerAdapter 用于处理入站 I/O 事件。
 *          ChannelOutboundHandlerAdapter 用于处理出站 I/O 操作。
 *          ChannelDuplexHandler 用于处理入站和出站事件。
 * 【ChannelHandlerContext】：
 *      保存 Channel 相关的所有上下文信息，同时关联一个 ChannelHandler 对象。
 *      ChannelHandlerContext中包含了一个具体的事件处理器ChannelHandler，同时ChannelHandlerContext中也绑定了对应的pipeline和channel的信息。
 * 【ChannelPipeline】：
 *      在Channel创建的时候，会同时创建ChannelPipeline，在ChannelPipeline中也会持有Channel的引用
 *      保存 ChannelHandler 的 List，用于处理或拦截 Channel 的入站事件和出站操作。
 *      ChannelPipeline 实现了一种高级形式的拦截过滤器模式，使用户可以完全控制事件的处理方式，以及 Channel 中各个的 ChannelHandler 如何相互交互。
 *      每个ChannelHandler通过add方法加入到ChannelPipeline中去的时候，会创建一个对应的ChannelHandlerContext，并且绑定，
 *      ChannelPipeline实际维护的是ChannelHandlerContext 的关系
 *
 * 在 Netty 中每个 Channel 都有且仅有一个 ChannelPipeline 与之对应。一个 Channel 包含了一个 ChannelPipeline，而 ChannelPipeline 中又维护了一个由
 * ChannelHandlerContext 组成的双向链表，并且每个 ChannelHandlerContext 中又关联着一个 ChannelHandler。入站事件和出站事件在一个双向链表中，入站事件会
 * 从链表 head 往后传递到最后一个入站的 handler，出站事件会从链表 tail 往前传递到最前一个出站的 handler，两种类型的 handler 互不干扰。
 *      1、每个Channel会绑定一个ChannelPipeline，ChannelPipeline中也会持有Channel的引用
 *      2、ChannelPipeline持有ChannelHandlerContext链路，保留ChannelHandlerContext的头尾节点指针
 *      3、每个ChannelHandlerContext会对应一个ChannelHandler，也就相当于ChannelPipeline持有ChannelHandler链路
 *      4、ChannelHandlerContext同时也会持有ChannelPipeline引用，也就相当于持有Channel引用
 *      5、ChannelHandler链路会根据Handler的类型，分为InBound和OutBound两条链路
 *
 * 在Netty中，有两种发送消息的方式：
 *      直接写到Channel中去，ChannelHandlerContext引用了ChannelPipeline，所以也能间接操作channel的方法；这种方式会从ChannelPipeline的尾端开始流动。
 *      写到ChannelHandlerContext对象中；这种方式发送消息会从ChannelPipeline的下一个ChannelHandler开始流动执行。
 *
 * 【EventLoopGroup】
 *      1、一个EventLoopGroup当中会包含一个或多个EventLoop；
 *      2、EventLoop在它的生命周期当中都只会与唯一一个Thread进行绑定；
 *      3、所有由EventLoop所处理的各种I/O事件都将在它所关联的那个Thread上进行处理；
 *      4、一个Channel在它的整个生命周期中会注册在一个EventLoop上；
 *      5、一个EventLoop在运行过程中，会被分配到一个或者多个Channel；
 *
 * 在Netty中，Channel的实现一定是线程安全的，基于此，我们可以存储一个Channel的引用，并且在需要向远程端点发送数据时，通过这个引用来调用Channel相应的方法；即便当时有
 * 很多线程都在使用它也不会出现多线程的问题；而且，消息一定会按照顺序发送。
 *
 * 在业务开发中，不要将长时间耗时的任务放入到EventLoop的执行队列中，因为它将会一直阻塞该线程对应的所有Channel上的其他执行任务，如果我们要进行阻塞调用或耗时的操作，那么我们
 * 就需要使用一个专门的EventExecutor（业务线程池）。
 *      1、可以自定义线程池处理
 *      2、借助于Netty提供的向ChannelPipeline添加ChannelHandler时调用的addLast方法来传递EventExecutor。
 *      默认情况下，ChannelHandler中的回调方法都是由I/O线程所执行，如果调用了ChannelPipeline addLast(EventExecutorGroup group, ChannelHandler... handlers);
 *      那么，ChannelHandler中的回调方法就是由参数中的group线程组来执行。
 *
 * 【ChannelFuture】
 *      JDK所提供的Future只能通过手工的方式检查执行结果，而且这个操作时阻塞的，Netty则对ChannelFuture进行了增强，通过ChannelFutureListener以回调的方式获取执行结果，
 *      ChannelFutureListener方法中的operationComplete方法是由I/O线程执行的，因此要注意的是不要在这里进行耗时的操作，否则需要通过另外的线程或线程池来执行。
 *
 */
public class Notes {

    public static void main(String[] args) {

    }

























}
