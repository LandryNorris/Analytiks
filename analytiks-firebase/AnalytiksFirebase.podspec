Pod::Spec.new do |spec|
    spec.name                     = 'AnalytiksFirebase'
    spec.version                  = '0.0.1'
    spec.homepage                 = ''
    spec.source                   = { :http=> ''}
    spec.authors                  = ''
    spec.license                  = ''
    spec.summary                  = ''
    spec.vendored_frameworks      = 'build/cocoapods/framework/AnalytiksFirebase.framework'
    spec.libraries                = 'c++'
                
    spec.dependency 'FirebaseAnalytics'
                
    spec.pod_target_xcconfig = {
        'KOTLIN_PROJECT_PATH' => ':analytiks-firebase',
        'PRODUCT_MODULE_NAME' => 'AnalytiksFirebase',
    }
                
    spec.script_phases = [
        {
            :name => 'Build AnalytiksFirebase',
            :execution_position => :before_compile,
            :shell_path => '/bin/sh',
            :script => <<-SCRIPT
                if [ "YES" = "$OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED" ]; then
                  echo "Skipping Gradle build task invocation due to OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED environment variable set to \"YES\""
                  exit 0
                fi
                set -ev
                REPO_ROOT="$PODS_TARGET_SRCROOT"
                "$REPO_ROOT/../../../../../tmp/wrap628loc/gradlew" -p "$REPO_ROOT" $KOTLIN_PROJECT_PATH:syncFramework \
                    -Pkotlin.native.cocoapods.platform=$PLATFORM_NAME \
                    -Pkotlin.native.cocoapods.archs="$ARCHS" \
                    -Pkotlin.native.cocoapods.configuration="$CONFIGURATION"
            SCRIPT
        }
    ]
                
end