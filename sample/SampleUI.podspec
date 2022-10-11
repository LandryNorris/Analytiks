Pod::Spec.new do |spec|
    spec.name                     = 'SampleUI'
    spec.version                  = '0.0.1'
    spec.homepage                 = 'https://github.com/LandryNorris/Analytiks'
    spec.source                   = { :http=> ''}
    spec.authors                  = ''
    spec.license                  = 'Apache 2.0'
    spec.summary                  = 'Firebase Analytics logging for KMM on Android and iOS'
    spec.vendored_frameworks      = 'build/cocoapods/framework/SampleUI.framework'
    spec.libraries                = 'c++'
    spec.ios.deployment_target = '11.0'
    spec.dependency 'FirebaseAnalytics'
                
    spec.pod_target_xcconfig = {
        'KOTLIN_PROJECT_PATH' => ':sample',
        'PRODUCT_MODULE_NAME' => 'SampleUI',
    }
                
    spec.script_phases = [
        {
            :name => 'Build SampleUI',
            :execution_position => :before_compile,
            :shell_path => '/bin/sh',
            :script => <<-SCRIPT
                if [ "YES" = "$OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED" ]; then
                  echo "Skipping Gradle build task invocation due to OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED environment variable set to \"YES\""
                  exit 0
                fi
                set -ev
                REPO_ROOT="$PODS_TARGET_SRCROOT"
                "$REPO_ROOT/../gradlew" -p "$REPO_ROOT" $KOTLIN_PROJECT_PATH:syncFramework \
                    -Pkotlin.native.cocoapods.platform=$PLATFORM_NAME \
                    -Pkotlin.native.cocoapods.archs="$ARCHS" \
                    -Pkotlin.native.cocoapods.configuration="$CONFIGURATION"
            SCRIPT
        }
    ]
                
end