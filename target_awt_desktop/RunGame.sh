cd ..
if ! [[ -f "gradlew" ]]; then
  gradle wrapper
fi;
chmod +x gradlew
./gradlew :target_awt_desktop:run $@
