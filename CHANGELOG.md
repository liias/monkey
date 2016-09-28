# Changelog

#### 0.3.2
* Potential fix for issue #2 (running in simulator on mac)
* Allow to set minimum target SDK version for module, used when compiling

#### 0.3.1
* Grammar: Allow statements on top (global) level
* Grammar: Allow catch (ex instanceof Y) 
* Grammar: Allow enum value to be a signed integer
* Parse more doc tags on doc comment (Ctrl+Q)
* Allow additional run parameters
* Fix running on Linux (use "wine cmd /c" instead of "wine" to run .bat), though not very reliable

#### 0.3.0

* Highlight keywords: this, self, $
* Highlight elements: symbols and annotations
* Highlight char literals and add some validations (length 1)
* Add run test icon for methods (will just generate module-wide test run configuration atm)
* Add generate new test method action (i.e alt+insert)

#### 0.2.9

* Fix block comments syntax error
* Show parameter info (ctrl+P) for methods declared in other files

#### 0.2.8

* Support importing eclipse project
* Exclude -a, -i, -u, -p compile arguments if using SDK version 2.1+

#### 0.2.7

* Fix compiling if SDK version does not support unit tests

#### 0.2.6

* Support Run No Evil test framework (you need to manually create test configuration)
* Add Go to class and module (Ctrl+N) and Go to symbol (Ctrl+Alt+Shift+N) support

#### 0.2.5

* Support developer key introduced in Connect IQ SDK 1.3 and 2.1

#### 0.2.4

* Allow run app action to build prg and copy it to device (currently no release prg)
* Parse SDK skeleton from the SDK documentation
* Added "Browse API Documentation" to Connect IQ menu
* Parse module references (still very dumb, ref search is only by name)
* Take devices list from SDK

#### 0.2.3

* Added App Settings window tool bar to the right
* Improved creating project/module from existing sources.
Now marking resource directories, setting target device and creating run configuration

#### 0.2.2

* Improved creating project/module from existing sources
* Improved running app to wait if simulator is not yet running
* Fix issue#1 - ignore modules which are not of Connect IQ type
* Require JRE instead of JDK for Monkey compiler

#### 0.2.1

* Built for IDEA 2016.1
* Fixed building when IDEA runs form different drive than project

#### 0.2

* Reworked language part - references and declarations now work in limited conditions
* Added very limited completion support
* Show compilation warnings and errors with source file and line
* Create SDK skeleton
* Show parameter info