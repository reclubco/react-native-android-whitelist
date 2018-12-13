
import { NativeModules, Platform } from 'react-native'

const { RNAndroidWhitelist } = NativeModules

const alert = (config) => {
  if (Platform.OS === 'android') {
    RNAndroidWhitelist.AlertIfWhitelist(config.title, config.text, config.doNotShowAgainText, config.positiveText, config.negativeText)
  }
}

export default { alert }

