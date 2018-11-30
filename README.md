
# react-native-android-whitelist

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
import RNReactNativeAndroidWhitelist from 'react-native-android-whitelist';

// TODO: What to do with the module?
RNReactNativeAndroidWhitelist;
```
  
