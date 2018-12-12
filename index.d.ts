
import { NativeModules, Platform } from 'react-native'

const { RNAndroidWhitelist } = NativeModules

class AndroidWhitelist {
  static alert = (config: any) => {
    if (Platform.OS === 'android') {
      RNAndroidWhitelist.AlertIfWhitelist(config.title, config.text, config.doNotShowAgainText, config.positiveText, config.negativeText)
    }
  }
}

export default AndroidWhitelist
