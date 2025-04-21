import UIKit

@objc public class ThemeObserver: NSObject {
    @objc public static func isDarkMode() -> Bool {
        if #available(iOS 12.0, *) {
            return UITraitCollection.current.userInterfaceStyle == .dark
        }
        return false
    }
}

@objc public static func setTheme(theme: String) {
    UserDefaults.standard.set(theme, forKey: "appTheme")
}

@objc public static func getTheme() -> String {
    return UserDefaults.standard.string(forKey: "appTheme") ?? "SYSTEM"
}