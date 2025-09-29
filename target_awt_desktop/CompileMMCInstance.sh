rm -rf dist/mmc_instance.zip
mkdir -p dist
mkdir -p build
cp -r ../src/mmc_instance build/mmc
cp ../src/main/resources/icon.png build/mmc/icon.png
cd build/mmc
zip -r ../../dist/mmc_instance.zip .
cd ../..
rm -rf build/mmc
rm -rf build