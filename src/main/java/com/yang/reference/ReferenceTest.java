package com.yang.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Reference主要是负责内存的一个状态，当然它还和java虚拟机，垃圾回收器打交道。
 * Reference类首先把内存分为4种状态Active（活跃状态）、Pending（准备回收状态）、Enqueued（已回收状态）、Inactive（最终状态）
 *
 * Active：一般来说内存一开始被分配的状态都是 Active，
 * Pending：大概是指快要被放进队列的对象，也就是马上要回收的对象，
 * Enqueued：就是对象的内存已经被回收了，我们已经把这个对象放入到一个队列中，方便以后我们查询某个对象是否被回收，
 * Inactive：就是最终的状态，不能再变为其它状态。
 * <p>
 * ReferenceQueue引用队列，当检测到对象的可到达性更改时，垃圾回收器将已注册的引用对象添加到队列中，
 * ReferenceQueue实现了入队（enqueue）和出队（poll），还有remove操作，内部元素head就是泛型的Reference。
 */
public class ReferenceTest {

    public static void main(String[] args) throws Exception {
        // 创建一个引用队列
        ReferenceQueue queue = new ReferenceQueue();

        // 创建弱引用，此时状态为Active，并且Reference.pending为空，当前Reference.queue = 上面创建的queue，并且next=null
        WeakReference reference = new WeakReference(new Object(), queue);
        System.out.println(reference);
        // 当GC执行后，由于是弱引用，所以回收该object对象，并且置于pending上，此时reference的状态为PENDING
        System.gc();

        /* ReferenceHandler从pending中取下该元素，并且将该元素放入到queue中，此时Reference状态为ENQUEUED，reference.queue = reference.ENQUEUED */

        /* 当从queue里面取出该元素，则变为INACTIVE，reference.queue = reference.NULL */
        Reference reference1 = queue.remove();
        System.out.println(reference1);
        System.out.println(reference1.get());
    }
}
