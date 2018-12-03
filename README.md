
# react-native-android-whitelist
Many android manufactuers have power saving optimizations that prevents applications from receiving push notifications while the app is not active.  This package will help you alert the user to whitelist your app so that push notifications will arrive more reliably.

Supported Manufacturers:

| Manufacturer 	| Power Saving Feature       	|
|--------------	|----------------------------	|
| Asus         	| Auto-Start Manager         	|
| MIUI         	| Auto-Start Manager         	|
| Letv         	| Auto-Boot Manager          	|
| Huawei       	| Protected Apps             	|
| Coloros      	| Startup App List           	|
| Oppo         	| Startup App                	|
| Vivo         	| Background Startup Manager 	|
| Samsung      	| Battery Activity           	|


## Getting started

`$ npm install react-native-android-whitelist --save`

### Mostly automatic installation

`$ react-native link react-native-android-whitelist`

### Manual installation

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNReactNativeAndroidWhitelistPackage;` to the imports at the top of the file
  - Add `new RNReactNativeAndroidWhitelistPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-android-whitelist'
  	project(':react-native-android-whitelist').projectDir = new File(rootProject.projectDir, 	'../node_modulesreact-native-android-whitelist/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-android-whitelist')
  	```

## Usage


```javascript
import RNAndroidWhitelist from 'react-native-android-whitelist';

const config = {
  title: "Android Whitelist",
  text: "To ensure timely delivery of push notifications, please whitelist our app.",
  doNotShowAgainText: "Do not show again",
  positiveText: "Whitelist",
  negativeText: "Cancel"
};

RNAndroidWhitelist.alert(config);
```
  
