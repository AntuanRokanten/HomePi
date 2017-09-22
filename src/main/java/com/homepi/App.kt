package com.homepi

import com.homepi.gpio.GpioMock
import com.homepi.gpio.led.LedSet
import com.homepi.gpio.led.impl.Rl50Led
import com.homepi.gpio.sensor.MotionSensor
import com.homepi.gpio.sensor.TemperatureAndHumiditySensor
import com.homepi.gpio.sensor.impl.Dht11Sensor
import com.homepi.gpio.sensor.impl.MockMotionSensor
import com.homepi.gpio.sensor.impl.MockTempAndHumSensor
import com.homepi.gpio.sensor.impl.Sr501Sensor
import com.homepi.service.objectdetection.ObjectDetector
import com.homepi.service.objectdetection.impl.OpenImajFaceDetector
import com.pi4j.io.gpio.GpioController
import com.pi4j.io.gpio.GpioFactory
import com.pi4j.io.gpio.RaspiPin.*
import org.bytedeco.javacv.FrameGrabber
import org.bytedeco.javacv.OpenCVFrameGrabber
import org.openimaj.image.ImageUtilities
import org.openimaj.image.colour.Transforms
import org.openimaj.image.processing.face.detection.HaarCascadeDetector
import org.openimaj.video.capture.VideoCapture
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InjectionPoint
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Scope
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
import java.io.ByteArrayOutputStream
import java.io.File
import java.net.URI
import java.nio.file.Paths


/**
 * @author ant
 */
@SpringBootApplication
open class App {

    @Bean
    @DefaultProfile
    open fun mockMotionSensor(): MotionSensor = MockMotionSensor(GPIO_29)

    @Bean
    @RpiProfile
    open fun motionSensor(gpio: GpioController): MotionSensor = Sr501Sensor(GPIO_29, gpio)

    @Bean
    @DefaultProfile
    open fun mockTempAndHumSensor(): TemperatureAndHumiditySensor = MockTempAndHumSensor(GPIO_17)

    @Bean
    @RpiProfile
    open fun temperatureAndHumiditySensor(): TemperatureAndHumiditySensor {
        val scriptUri = resourceUri("/python/AdafruitDHT.py")
        return Dht11Sensor(GPIO_17, Paths.get(scriptUri))
    }

    @Bean
    @DefaultProfile
    open fun mokcGpioController(): GpioController = GpioMock()

    @Bean
    @RpiProfile
    open fun gpioController(): GpioController = GpioFactory.getInstance()

    @Bean("tempAndHumScheduler")
    open fun temperatureTaskScheduler(): TaskScheduler = ThreadPoolTaskScheduler()

    @Bean
    open fun faceRecognizer(): ObjectDetector {
        val cascadeUri = resourceUri("/cv/haarcascade_frontalface_alt.xml")
//        return FaceDetector(Paths.get(cascadeUri))
        return OpenImajFaceDetector(Paths.get(cascadeUri))
    }

    @Bean
    @RpiProfile
    @LinuxProfile
    open fun grabber(): FrameGrabber {
        val grabber = OpenCVFrameGrabber("/dev/video0")
//        grabber.format = "video4linux2"
        return grabber
    }

    @Bean
    @WindowsProfile
    open fun winGrabber(): FrameGrabber {
        return OpenCVFrameGrabber(0)
    }

    @Bean
    @Scope("prototype")
    open fun logger(ip: InjectionPoint): Logger =
            LoggerFactory.getLogger(ip.member.name) // warning: will not work with field injection

    @Bean
    @RpiProfile
    open fun ledSet(gpio: GpioController): LedSet =
            LedSet(Rl50Led(GPIO_22, gpio), Rl50Led(GPIO_23, gpio), Rl50Led(GPIO_24, gpio), Rl50Led(GPIO_25, gpio))

    @Bean
    @DefaultProfile
    open fun mockLedSet(gpio: GpioController): LedSet =
            LedSet()

    private fun resourceUri(path: String): URI {
        return this.javaClass.getResource(path).toURI() ?: throw ExceptionInInitializerError("Unable to find a resource in the following path $path")
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(App::class.java, *args)
}

fun main12(args: Array<String>) {
    val detector = HaarCascadeDetector()

    val videoDevices = VideoCapture.getVideoDevices()
    val videoCapture = VideoCapture(320, 240)

    for (mbfImage in videoCapture) {
        val detectFaces = detector.detectFaces(Transforms.calculateIntensity(mbfImage))

        if (detectFaces.size > 0) {
            ImageUtilities.write(detectFaces.get(0).facePatch, File("/home/pi/face.png"))
            ImageUtilities.write(detectFaces.get(0).facePatch, "jpeg", ByteArrayOutputStream())
        }

        ImageUtilities.write(mbfImage, File("/home/pi/img12.png"))
    }
}

//fun main(args: Array<String>) {
//    val cap = opencv_videoio.VideoCapture("/dev/video0")
////    println(cap.open(0))
//    Thread.sleep(100)
////    println(cap.isOpened)
//    val mat = opencv_core.Mat()
//
//    cap.grab()
//    cap.read(mat)
//    imwrite("/home/ant/frame.jpeg", mat)
//
//
//    val classifier = opencv_objdetect.CascadeClassifier("C:\\haarcascade_frontalface_alt.xml")
//    val rectVector = opencv_core.RectVector()
//    classifier.detectMultiScale(mat, rectVector)
//
////    mat.release()
////    cap.close()
//    cap.release()
//}