package com.yang.designpattern.headfirst.facade;

/**
 * 外观模式：提供了一个统一的接口，用来访问子系统中的一群接口。外观定义了一个高层接口，让子系统更容易使用。
 */
public class HomeTheaterFacade {
    /**
     * 这就是组合，我们会用到的子系统组件全部都在这里
     */
    private Amplifier amp;
    private DvdPlayer dvd;
    private Projector projector;
    private TheaterLights lights;
    private Screen screen;
    private PopcornPopper popper;

    //外观将子系统中每一个组件的引用都传入它的构造器中。然后外观把它们赋值给相应的实例变量
    HomeTheaterFacade(Amplifier amp, DvdPlayer dvd, Projector projector, TheaterLights lights,
                      Screen screen, PopcornPopper popper) {
        this.amp = amp;
        this.dvd = dvd;
        this.projector = projector;
        this.lights = lights;
        this.screen = screen;
        this.popper = popper;
    }

    //watchMovie()将我们之前手动进行的每项任务依次处理，请注意，每项任务都是委托子系统中相应的组件处理的
    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");
        popper.on();
        popper.pop();
        lights.dim(10);
        screen.down();
        projector.on();
        projector.wideScreenMode();
        amp.on();
        amp.setDvd(dvd);
        amp.setSurroundSound();
        amp.setVolume(10);
        dvd.on();
        dvd.play(movie);
    }

    //而endMovie()负责关闭一切，每项任务也都是委托子系统中合适的组件处理的
    public void endMovie() {
        System.out.println("Shutting movie theater down...");
        popper.off();
        lights.on();
        screen.up();
        projector.off();
        amp.off();
        dvd.stop();
        dvd.eject();
        dvd.off();
    }
}
