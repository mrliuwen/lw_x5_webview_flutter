#import "X5WebviewPlugin.h"
#import "OCX5WebviewPlugin.h"

@implementation X5WebViewPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [OCX5WebviewPlugin registerWithRegistrar:registrar];
}
@end
