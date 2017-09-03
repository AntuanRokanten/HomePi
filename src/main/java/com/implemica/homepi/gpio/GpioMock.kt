package com.implemica.homepi.gpio

import com.pi4j.io.gpio.*
import com.pi4j.io.gpio.event.GpioPinListener
import com.pi4j.io.gpio.trigger.GpioTrigger

/**
 * @author ant
 */
class GpioMock : GpioController {
    override fun setValue(p0: Double, vararg p1: GpioPinAnalogOutput?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getProvisionedPins(): MutableCollection<GpioPin> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pulse(p0: Long, vararg p1: GpioPinDigitalOutput?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionAnalogInputPin(p0: GpioProvider?, p1: Pin?, p2: String?): GpioPinAnalogInput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionAnalogInputPin(p0: GpioProvider?, p1: Pin?): GpioPinAnalogInput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionAnalogInputPin(p0: Pin?, p1: String?): GpioPinAnalogInput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionAnalogInputPin(p0: Pin?): GpioPinAnalogInput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isHigh(vararg p0: GpioPinDigital?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unexportAll() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addListener(p0: GpioPinListener?, vararg p1: GpioPinInput?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addListener(p0: Array<out GpioPinListener>?, vararg p1: GpioPinInput?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setState(p0: PinState?, vararg p1: GpioPinDigitalOutput?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setState(p0: Boolean, vararg p1: GpioPinDigitalOutput?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addTrigger(p0: GpioTrigger?, vararg p1: GpioPinInput?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addTrigger(p0: Array<out GpioTrigger>?, vararg p1: GpioPinInput?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unprovisionPin(vararg p0: GpioPin?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isPullResistance(p0: PinPullResistance?, vararg p1: GpioPin?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeListener(p0: GpioPinListener?, vararg p1: GpioPinInput?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeListener(p0: Array<out GpioPinListener>?, vararg p1: GpioPinInput?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionDigitalOutputPin(p0: GpioProvider?, p1: Pin?, p2: String?, p3: PinState?): GpioPinDigitalOutput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionDigitalOutputPin(p0: GpioProvider?, p1: Pin?, p2: PinState?): GpioPinDigitalOutput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionDigitalOutputPin(p0: GpioProvider?, p1: Pin?, p2: String?): GpioPinDigitalOutput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionDigitalOutputPin(p0: GpioProvider?, p1: Pin?): GpioPinDigitalOutput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionDigitalOutputPin(p0: Pin?, p1: String?, p2: PinState?): GpioPinDigitalOutput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionDigitalOutputPin(p0: Pin?, p1: PinState?): GpioPinDigitalOutput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionDigitalOutputPin(p0: Pin?, p1: String?): GpioPinDigitalOutput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionDigitalOutputPin(p0: Pin?): GpioPinDigitalOutput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unexport(vararg p0: Pin?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unexport(vararg p0: GpioPin?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isLow(vararg p0: GpioPinDigital?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionSoftPwmOutputPin(p0: GpioProvider?, p1: Pin?, p2: String?, p3: Int): GpioPinPwmOutput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionSoftPwmOutputPin(p0: GpioProvider?, p1: Pin?, p2: Int): GpioPinPwmOutput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionSoftPwmOutputPin(p0: GpioProvider?, p1: Pin?, p2: String?): GpioPinPwmOutput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionSoftPwmOutputPin(p0: GpioProvider?, p1: Pin?): GpioPinPwmOutput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionSoftPwmOutputPin(p0: Pin?, p1: String?, p2: Int): GpioPinPwmOutput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionSoftPwmOutputPin(p0: Pin?, p1: Int): GpioPinPwmOutput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionSoftPwmOutputPin(p0: Pin?, p1: String?): GpioPinPwmOutput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionSoftPwmOutputPin(p0: Pin?): GpioPinPwmOutput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMode(p0: GpioPin?): PinMode {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeAllTriggers() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getValue(p0: GpioPinAnalog?): Double {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionDigitalMultipurposePin(p0: GpioProvider?, p1: Pin?, p2: String?, p3: PinMode?, p4: PinPullResistance?): GpioPinDigitalMultipurpose {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionDigitalMultipurposePin(p0: GpioProvider?, p1: Pin?, p2: PinMode?, p3: PinPullResistance?): GpioPinDigitalMultipurpose {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionDigitalMultipurposePin(p0: GpioProvider?, p1: Pin?, p2: String?, p3: PinMode?): GpioPinDigitalMultipurpose {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionDigitalMultipurposePin(p0: GpioProvider?, p1: Pin?, p2: PinMode?): GpioPinDigitalMultipurpose {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionDigitalMultipurposePin(p0: Pin?, p1: String?, p2: PinMode?, p3: PinPullResistance?): GpioPinDigitalMultipurpose {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionDigitalMultipurposePin(p0: Pin?, p1: PinMode?, p2: PinPullResistance?): GpioPinDigitalMultipurpose {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionDigitalMultipurposePin(p0: Pin?, p1: String?, p2: PinMode?): GpioPinDigitalMultipurpose {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionDigitalMultipurposePin(p0: Pin?, p1: PinMode?): GpioPinDigitalMultipurpose {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun low(vararg p0: GpioPinDigitalOutput?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPullResistance(p0: GpioPin?): PinPullResistance {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getState(p0: GpioPinDigital?): PinState {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeTrigger(p0: GpioTrigger?, vararg p1: GpioPinInput?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeTrigger(p0: Array<out GpioTrigger>?, vararg p1: GpioPinInput?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setShutdownOptions(p0: GpioPinShutdown?, vararg p1: GpioPin?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setShutdownOptions(p0: Boolean?, vararg p1: GpioPin?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setShutdownOptions(p0: Boolean?, p1: PinState?, vararg p2: GpioPin?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setShutdownOptions(p0: Boolean?, p1: PinState?, p2: PinPullResistance?, vararg p3: GpioPin?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setShutdownOptions(p0: Boolean?, p1: PinState?, p2: PinPullResistance?, p3: PinMode?, vararg p4: GpioPin?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isState(p0: PinState?, vararg p1: GpioPinDigital?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionDigitalInputPin(p0: GpioProvider?, p1: Pin?, p2: String?, p3: PinPullResistance?): GpioPinDigitalInput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionDigitalInputPin(p0: GpioProvider?, p1: Pin?, p2: PinPullResistance?): GpioPinDigitalInput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionDigitalInputPin(p0: GpioProvider?, p1: Pin?, p2: String?): GpioPinDigitalInput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionDigitalInputPin(p0: GpioProvider?, p1: Pin?): GpioPinDigitalInput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionDigitalInputPin(p0: Pin?, p1: String?, p2: PinPullResistance?): GpioPinDigitalInput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionDigitalInputPin(p0: Pin?, p1: PinPullResistance?): GpioPinDigitalInput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionDigitalInputPin(p0: Pin?, p1: String?): GpioPinDigitalInput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionDigitalInputPin(p0: Pin?): GpioPinDigitalInput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setMode(p0: PinMode?, vararg p1: GpioPin?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionAnalogOutputPin(p0: GpioProvider?, p1: Pin?, p2: String?, p3: Double): GpioPinAnalogOutput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionAnalogOutputPin(p0: GpioProvider?, p1: Pin?, p2: Double): GpioPinAnalogOutput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionAnalogOutputPin(p0: GpioProvider?, p1: Pin?, p2: String?): GpioPinAnalogOutput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionAnalogOutputPin(p0: GpioProvider?, p1: Pin?): GpioPinAnalogOutput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionAnalogOutputPin(p0: Pin?, p1: String?, p2: Double): GpioPinAnalogOutput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionAnalogOutputPin(p0: Pin?, p1: Double): GpioPinAnalogOutput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionAnalogOutputPin(p0: Pin?, p1: String?): GpioPinAnalogOutput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionAnalogOutputPin(p0: Pin?): GpioPinAnalogOutput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isExported(vararg p0: GpioPin?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setPullResistance(p0: PinPullResistance?, vararg p1: GpioPin?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getProvisionedPin(p0: Pin?): GpioPin {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getProvisionedPin(p0: String?): GpioPin {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isMode(p0: PinMode?, vararg p1: GpioPin?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun shutdown() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun high(vararg p0: GpioPinDigitalOutput?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toggle(vararg p0: GpioPinDigitalOutput?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun export(p0: PinMode?, p1: PinState?, vararg p2: GpioPin?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun export(p0: PinMode?, vararg p1: GpioPin?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isShutdown(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionPin(p0: GpioProvider?, p1: Pin?, p2: String?, p3: PinMode?, p4: PinState?): GpioPin {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionPin(p0: GpioProvider?, p1: Pin?, p2: String?, p3: PinMode?): GpioPin {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionPin(p0: GpioProvider?, p1: Pin?, p2: PinMode?): GpioPin {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionPin(p0: Pin?, p1: String?, p2: PinMode?): GpioPin {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionPin(p0: Pin?, p1: PinMode?): GpioPin {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeAllListeners() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionPwmOutputPin(p0: GpioProvider?, p1: Pin?, p2: String?, p3: Int): GpioPinPwmOutput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionPwmOutputPin(p0: GpioProvider?, p1: Pin?, p2: Int): GpioPinPwmOutput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionPwmOutputPin(p0: GpioProvider?, p1: Pin?, p2: String?): GpioPinPwmOutput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionPwmOutputPin(p0: GpioProvider?, p1: Pin?): GpioPinPwmOutput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionPwmOutputPin(p0: Pin?, p1: String?, p2: Int): GpioPinPwmOutput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionPwmOutputPin(p0: Pin?, p1: Int): GpioPinPwmOutput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionPwmOutputPin(p0: Pin?, p1: String?): GpioPinPwmOutput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provisionPwmOutputPin(p0: Pin?): GpioPinPwmOutput {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}