# HomePi

When deployed on Raspberry Pi, gathers information from the sensors connected and provides web interface for displaying data.

Also hosts [RPiHomeAutomationBot](https://telegram.me/RPiHomeAutomationBot) telegram bot which provides interface for querying sensors' state.

## Electronic components
### Sensors
* *Motion sensor* - HC-SR501  [Specs](https://www.mpja.com/download/31227sc.pdf)
* *Temperature-humidity sensor* - DHT11 [Specs](http://www.robotshop.com/media/files/pdf/dht11.pdf)
### Leds
RL50-TUR1TYG136 [Specs](http://polaris-light.com.ua/image/data/pdf/496.pdf)

**[Full Bill of Materials](https://github.com/AntuanRokanten/HomePi/blob/master/circuit/HomePi_bom.html)**

## Circuit
![Circuit](https://github.com/AntuanRokanten/HomePi/blob/master/circuit/HomePi_circuit.jpg)

## Installation
In order to use DHT11 sensor, install Python library
 
```
sudo apt-get install build-essential python-dev python-openssl
git clone https://github.com/adafruit/Adafruit_Python_DHT.git
cd Adafruit_Python_DHT
sudo python setup.py install
```



## Author
**[Anton G.](https://github.com/AntuanRokanten)**