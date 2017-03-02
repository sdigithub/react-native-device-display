#import <React/RCTBridge.h>

@interface DisplayDeviceUtil : NSObject <RCTBridgeModule>

- (NSDictionary *)getDimensions;

@end
