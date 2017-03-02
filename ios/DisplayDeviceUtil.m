#import "DisplayDeviceUtil.h"
#import <React/RCTBridge.h>
#import <React/RCTEventDispatcher.h>
#import <React/RCTUtils.h>

@implementation DisplayDeviceUtil

RCT_EXPORT_MODULE();

@synthesize bridge = _bridge;

- (instancetype)init {
  if ((self = [super init])) {
    [[NSNotificationCenter defaultCenter] addObserver:self
                                             selector:@selector(displayOrientationDidChange:)
                                                 name:UIDeviceOrientationDidChangeNotification
                                               object:nil];
  }

  return self;
}

- (void)dealloc {
  [[NSNotificationCenter defaultCenter] removeObserver:self];
}

- (void)displayOrientationDidChange:(NSNotification*)note {
  CGSize frameSize = [UIScreen mainScreen].applicationFrame.size;

  /* For Non-Orientation Dependant Versions */
  if ((NSFoundationVersionNumber <= NSFoundationVersionNumber_iOS_7_1)
      && UIInterfaceOrientationIsLandscape([UIApplication sharedApplication].statusBarOrientation)) {
    frameSize = CGSizeMake(frameSize.height, frameSize.width);
  }

  UIDeviceOrientation deviceOrientation = [[UIDevice currentDevice] orientation];
  NSDictionary *dimensions = @{ @"width": @(frameSize.width), @"height": @(frameSize.height),      @"orientation": @(deviceOrientation) };
  NSLog(@"%@", dimensions);
  [_bridge.eventDispatcher sendDeviceEventWithName:@"orientationDidChange" body:dimensions];
}

- (NSDictionary *)constantsToExport {
  BOOL isPhone = [[UIDevice currentDevice] userInterfaceIdiom] == UIUserInterfaceIdiomPhone;
  BOOL isTablet = [[UIDevice currentDevice] userInterfaceIdiom] == UIUserInterfaceIdiomPad;

  return @{
    @"isPhone" : @(isPhone),
    @"isTablet" : @(isTablet)
  };
}

@end
