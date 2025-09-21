cd ..
if ! [[ -f "gradlew" ]]; then
  gradle wrapper
fi;
chmod +x gradlew
./gradlew :target_teavm_javascript:generateJavaScript $@
