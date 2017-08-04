
module.exports = {
    CLIENT_DIR: "src-client",
    OUTPUT_DIR: "src/public",
    OUTPUT_ARTIFACT: "bundle",
    isDevelopment: function(env) {
        return env.debug === 'true';
    }
};
