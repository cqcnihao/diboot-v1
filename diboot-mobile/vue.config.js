module.exports = {
  baseUrl: '/',
  outputDir: 'dist',
  assetsDir: 'static',
  devServer: {
    disableHostCheck: true,
    port: 9000,
    proxy: {
      '/rest': {
        target: 'http://localhost:8080/rest',
        changeOrigin: true,
        pathRewrite: {
          '^/rest': '/',
        },
      },
    },
  },
};
