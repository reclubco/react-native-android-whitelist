
import { NativeModules, Platform } from 'react-native'

const { RNAndroidWhitelist } = NativeModules

class AndroidWhitelist {
  static alert(config: any): void
}

export default AndroidWhitelist
