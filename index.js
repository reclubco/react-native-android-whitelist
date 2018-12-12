
import { NativeModules, Platform } from 'react-native'

const { RNAndroidWhitelist } = NativeModules

export const alert = (config) => {
  if (Platform.OS === 'android') {
    RNAndroidWhitelist.AlertIfWhitelist(config.title, config.text, config.doNotShowAgainText, config.positiveText, config.negativeText)
  }
}