//
//  OCX5WebviewPlugin.m
//  x5_webview
//
//  Created by Himin on 2020/2/17.
//

#import "OCX5WebviewPlugin.h"

@implementation OCX5WebviewPlugin

+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar> *)registrar {
    FlutterMethodChannel* channel = [FlutterMethodChannel  methodChannelWithName:@"x5_webview" binaryMessenger:[registrar messenger]];
    OCX5WebviewPlugin *instance = [[OCX5WebviewPlugin alloc] init];
    [registrar addMethodCallDelegate:instance channel:channel];
}

- (void)handleMethodCall:(FlutterMethodCall *)call result:(FlutterResult)result {
    NSString *str = [NSString stringWithFormat:@"iOS %@",[UIDevice currentDevice].systemVersion];
    result(str);
}

@end
